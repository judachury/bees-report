package org.beesden.report.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import org.beesden.report.model.Project;
import org.beesden.report.model.User;
import org.beesden.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ProjectView extends ViewUtils {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dashboard(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to show dashboard");
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		String status = request.getParameter("status");
		String sort = request.getParameter("sort");
		String dbQuery = projectService.getQuery(null, null, status == null ? 0 : Integer.parseInt(status), sort == null ? "dueDate" : sort);
		Map<String, Integer> pagination = getPagination(request, projectService.count(dbQuery).intValue(), 12);
		model.addAttribute("pagination", pagination);
		model.addAttribute("projectList", projectService.findPaged(dbQuery, pagination));
		return getTemplate(model, request, "projectList", config, start);
	}

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public String projectCreate(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to create a new project");
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		String dbQuery = userService.getQuery(null, null, 1, "firstname");
		model.addAttribute("userList", userService.findAll(dbQuery));
		return getTemplate(model, request, "projectForm", config, start);
	}

	@RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
	public String projectRead(@PathVariable("projectId") String name, ModelMap model, HttpServletRequest request) {
		logger.info("Received request to show specific project: " + name);
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		String dbQuery = projectService.getQuery(null, name, null, null);
		Project project = projectService.findOne(dbQuery);
		if (project == null) {
			logger.error("No project found");
			return "redirect:/";
		}
		model.addAttribute("project", project);
		return getTemplate(model, request, "projectView", config, start);
	}

	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public String projectSubmit(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to save a project");
		Project project = new Project();
		// If update, get old hidden project information
		String projectId = request.getParameter("projectId");
		if (Utils.isNumeric(projectId)) {
			String dbQuery = projectService.getQuery(projectId, null, null, null);
			Project original = projectService.findOne(dbQuery);
			if (original != null) {
				project.setId(original.getId());
				project.setDateCreated(original.getDateCreated());
				project.setCreatedBy(original.getCreatedBy());
			}
		}
		// Basic information
		project.setName(request.getParameter("name").replaceAll("\\s+", ""));
		project.setSummary(request.getParameter("summary"));
		// Convert date strings (yyyy-mm-dd)
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			project.setDueDate(formatter.parse(request.getParameter("dueDate")));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		try {
			project.setTestDate(formatter.parse(request.getParameter("testDate")));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		// Check user information
		String developerId = request.getParameter("developer");
		String managerId = request.getParameter("manager");
		if (Utils.isNumeric(developerId)) {
			project.setLeadDeveloper(userService.findOne(userService.getQuery(request.getParameter("developer"), null, 1, null)));
		}
		if (Utils.isNumeric(managerId)) {
			project.setProjectManager(userService.findOne(userService.getQuery(request.getParameter("manager"), null, 1, null)));
		}
		// Check if valid
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Project>> errors = validator.validate(project);
		List<String> errorList = new ArrayList<String>();
		// Hibernate validation errors
		for (ConstraintViolation<Project> error : errors) {
			errorList.add(error.getMessageTemplate());
		}
		// Check if the name is unique
		if (!projectService.isNameAvailable(project.getName(), project.getId())) {
			logger.info("Project name is already in use");
			errorList.add("forms.name.duplicate");
		}
		// Return to form / view
		model.addAttribute("project", project);
		if (errorList.size() > 0) {
			logger.info("Project does not validate");
			model.addAttribute("errors", errorList);
			return projectCreate(model, request);
		} else {
			logger.info("Persisting project object");
			User currentUser = fetchUser();
			if (project.getDateCreated() == null) {
				project.setDateCreated(new Date());
				project.setCreatedBy(currentUser.getName());
			}
			project.setLastEdited(new Date());
			project.setLastEditedBy(currentUser.getName());
			project.setStatus(1);
			projectService.objectUpdate(project);
			return "redirect:/project/" + project.getName();
		}
	}

	@RequestMapping(value = "/project/{projectId}/update", method = RequestMethod.GET)
	public String projectUpdate(@PathVariable("projectId") String name, ModelMap model, HttpServletRequest request) {
		logger.info("Received request to update an existing project");
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		// Get the project, if it exists
		String dbQuery = projectService.getQuery(null, name, null, null);
		Project project = projectService.findOne(dbQuery);
		if (project == null) {
			logger.error("No project found");
			return "redirect:/";
		}
		model.addAttribute("project", project);
		// Get the list of users as well
		dbQuery = userService.getQuery(null, null, 1, "firstname");
		model.addAttribute("userList", userService.findAll(dbQuery));
		return getTemplate(model, request, "projectForm", config, start);
	}

}