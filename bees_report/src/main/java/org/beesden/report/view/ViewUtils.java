package org.beesden.report.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.beesden.report.model.User;
import org.beesden.utils.Utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

public class ViewUtils extends ViewServices {

	public List<String> addError(List<String> errors, String error) {
		errors.add(error);
		return errors;
	}

	public User fetchUser() {
		// Get customer information if logged in
		User user = new User();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			String dbQuery = userService.getQuery(null, auth.getName(), 1, null);
			user = userService.findOne(dbQuery);
		}
		return user;
	}

	public Map<String, Integer> getPagination(HttpServletRequest request, Integer size, Integer results) {
		// Object pagination
		Map<String, Integer> pagination = new HashMap<String, Integer>();
		// Check parameters
		String pageAttr = request.getParameter("page");
		Integer page = Utils.isNumeric(pageAttr) ? Integer.parseInt(pageAttr) : 1;
		String resultsAttr = request.getParameter("results");
		results = Utils.isNumeric(resultsAttr) ? Integer.parseInt(resultsAttr) : results;
		// Calculate pagination
		Integer first = (page - 1) * results;
		first = first < 0 ? 0 : first;
		first = first > size ? 1 : first;
		Integer last = first + results;
		last = last > size ? size : last;
		Integer pages = (int) Math.ceil((double) size / (double) results);
		// Add information to map
		pagination.put("page", page);
		pagination.put("pages", pages.intValue());
		pagination.put("results", results);
		pagination.put("first", first);
		pagination.put("last", last);
		pagination.put("size", size);
		return pagination;
	}

	public String getTemplate(ModelMap model, HttpServletRequest request, String layout, Map<String, Object> config, long time) {
		// Use ajax layout if requested
		Boolean ajaxRequest = request.getParameter("ajax") != null && request.getParameter("ajax").equals("true");
		Boolean jsonRequest = request.getParameter("json") != null && request.getParameter("json").equals("true");
		String template = ajaxRequest ? jsonRequest ? "json" : "ajax" : "default";
		model.addAttribute("template", template);
		model.addAttribute("layout", layout);
		// Get the template objects
		model.addAttribute("config", config);
		model.addAttribute("currentUser", fetchUser());
		request.getSession().setAttribute("messages", null);
		// Complete and log the request
		logger.info("Template : " + template);
		logger.info("Layout : " + layout);
		logger.info("Response time: " + (System.currentTimeMillis() - time) + "ms\n----------");
		return template + "." + layout;
	}

}