package org.beesden.report.view;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.beesden.report.model.User;
import org.beesden.report.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LoginView extends ViewUtils {

	@Autowired
	public EmailService emailService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to show login page");
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		return getTemplate(model, request, "login", config, start);
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String userPassword(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to show password recovery form");
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		return getTemplate(model, request, "password", config, start);
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String userPasswordReset(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to reset a password");
		List<String> errorList = new ArrayList<String>();
		// Get the user from the username
		String username = request.getParameter("username");
		String dbQuery = userService.getQuery(null, username, null, null);
		User user = userService.findOne(dbQuery);
		if (user == null) {
			// That user just doesn't exist
			errorList.add("login.invalid.user");
		} else if (user.getEmail() == null || user.getEmail().equals("")) {
			// That user doesn't have an email address
			errorList.add("login.invalid.email");
		} else {
			// Generate a new password and update the database with it
			String password = new BigInteger(130, new SecureRandom()).toString(32);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(password));
			user.setLastEdited(new Date());
			user.setLastEditedBy(username);
			// Send an email to let them know it's changed
			Map<String, Object> config = configService.getConfig(request);
			String subject = "Password reset information";
			String body = "Hi " + username + ",\n\nYour new password is " + password;
			try {
				// Update DB and add confirmation message once email is sent
				emailService.sendMail(config, user.getEmail(), subject, body);
				userService.objectUpdate(user);
				errorList.add("login.password.sent");
			} catch (Exception e) {
				logger.error(e.getMessage());
				errorList.add("login.email.error");
			}
		}

		model.addAttribute("errors", errorList);
		return userPassword(model, request);
	}

}