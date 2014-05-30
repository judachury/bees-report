package org.beesden.report.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.beesden.report.model.User;
import org.beesden.utils.Utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class UserView extends ViewUtils {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userForm(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to show user list");
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		return getTemplate(model, request, "userForm", config, start);
	}

	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String userList(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to show user list");
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		String dbQuery = userService.getQuery(null, null, status == null ? 0 : Integer.parseInt(status), sort == null ? "firstname" : sort);
		Map<String, Integer> pagination = getPagination(request, userService.count(dbQuery).intValue(), 12);
		model.addAttribute("pagination", pagination);
		model.addAttribute("userList", userService.findPaged(dbQuery, pagination));
		return getTemplate(model, request, "userList", config, start);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String userSubmit(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to save a user");
		List<String> errorList = new ArrayList<String>();
		User user = new User();
		// If update, get old hidden project information
		String userId = request.getParameter("userId");
		if (Utils.isNumeric(userId)) {
			String dbQuery = userService.getQuery(userId, null, null, null);
			User original = userService.findOne(dbQuery);
			if (original != null) {
				user.setId(original.getId());
				user.setDateCreated(original.getDateCreated());
				user.setCreatedBy(original.getCreatedBy());
			}
		}
		// Basic information
		user.setName(request.getParameter("username").replaceAll("\\s+", ""));
		user.setFirstname(request.getParameter("firstname"));
		user.setSurname(request.getParameter("surname"));
		user.setEmail(request.getParameter("email"));
		String status = request.getParameter("userStatus");
		if (Utils.isNumeric(status)) {
			user.setStatus(Integer.parseInt(status));
		}
		String authority = request.getParameter("userAuth");
		if (Utils.isNumeric(authority)) {
			user.setAuthority(Integer.parseInt(authority));
		}
		// Check and update password
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		if (password == null || !password.equals(password2)) {
			errorList.add("forms.password.match");
		} else {
			user.setPassword(password);
		}
		// Check if valid
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<User>> errors = validator.validate(user);
		// Hibernate validation errors
		for (ConstraintViolation<User> error : errors) {
			errorList.add(error.getMessageTemplate());
		}
		// Check if the name is unique
		if (!userService.isNameAvailable(user.getName(), user.getId())) {
			logger.info("Username is already in use");
			errorList.add("forms.username.duplicate");
		}
		// Return to form / view
		model.addAttribute("user", user);
		if (errorList.size() > 0) {
			logger.info("Tron fights for the users!");
			model.addAttribute("errors", errorList);
			return userForm(model, request);
		} else {
			logger.info("Persisting user object");
			User currentUser = fetchUser();
			if (user.getDateCreated() == null) {
				user.setDateCreated(new Date());
				user.setCreatedBy(currentUser.getName());
			}
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			user.setLastEdited(new Date());
			user.setLastEditedBy(currentUser.getName());
			userService.objectUpdate(user);
			if (user.getId() == currentUser.getId()) {
				logger.info("Logged in user has changed. You'll need to log in again");
				return "redirect:/logout";
			}
			return "redirect:/userlist";
		}
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String userUpdate(@PathVariable("userId") String name, ModelMap model, HttpServletRequest request) {
		logger.info("Received request to update an existing user");
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		// Get the project, if it exists
		String dbQuery = userService.getQuery(null, name, null, null);
		User user = userService.findOne(dbQuery);
		if (user == null) {
			logger.error("No project found");
			return "redirect:/userlist";
		}
		model.addAttribute("user", user);
		return getTemplate(model, request, "userForm", config, start);
	}

}