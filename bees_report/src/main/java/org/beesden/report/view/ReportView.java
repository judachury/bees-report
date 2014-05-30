package org.beesden.report.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import org.beesden.report.model.Report;
import org.beesden.report.model.ReportItem;
import org.beesden.report.model.User;
import org.beesden.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ReportView extends ViewUtils {

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String reportCreate(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to create a new report");
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		if (model.get("report") == null) {
			Report report = new Report();
			// Copy an existing report
			String copyReportId = request.getParameter("copy");
			if (copyReportId != null) {
				String dbQuery = reportService.getQuery(copyReportId, null, null, null);
				report = reportService.findOne(dbQuery);
			}
			// Select a project
			String projectId = request.getParameter("project");
			if (projectId != null) {
				String dbQuery = projectService.getQuery(projectId, null, null, null);
				Project project = projectService.findOne(dbQuery);
				report.setProject(project);
			}
			if (report == null || report.getProject() == null) {
				logger.error("No project found");
				return "redirect:/";
			}
			model.addAttribute("report", report);
		}
		return getTemplate(model, request, "reportForm", config, start);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public String reportSubmit(ModelMap model, HttpServletRequest request) {
		logger.info("Received request to save a report");
		Report report = new Report();
		List<ReportItem> items = new ArrayList<ReportItem>();
		// There should always be a project linked
		String projectId = request.getParameter("projectId");
		if (Utils.isNumeric(projectId)) {
			String dbQuery = projectService.getQuery(projectId, null, null, null);
			Project project = projectService.findOne(dbQuery);
			if (project != null) {
				report.setProject(project);
			}
		}
		// If update, get old hidden project information
		String reportId = request.getParameter("reportId");
		if (Utils.isNumeric(reportId)) {
			String dbQuery = reportService.getQuery(reportId, null, null, null);
			Report original = reportService.findOne(dbQuery);
			if (original != null) {
				report.setId(original.getId());
				report.setDateCreated(original.getDateCreated());
				report.setCreatedBy(original.getCreatedBy());
			}
		}
		// Basic information
		report.setName(request.getParameter("name"));
		report.setComments(request.getParameter("comments"));
		// Report items
		Integer average = 0;
		Set<String> parameterNames = request.getParameterMap().keySet();
		for (String paramKey : parameterNames) {
			if (paramKey.startsWith("itemName-")) {
				String index = paramKey.replace("itemName-", "");
				String key = request.getParameterValues(paramKey)[0];
				String value = request.getParameter("itemValue-" + index);
				if (key != null && !key.isEmpty() && Utils.isNumeric(value) && Utils.isNumeric(index)) {
					ReportItem item = new ReportItem();
					item.setName(key);
					item.setValue(value);
					item.setSortOrder(Integer.parseInt(index));
					item.setReport(report);
					items.add(item);
					average = average + Integer.parseInt(value);
				}
			}
		}
		// Maintain sort order of list, in case of error
		Collections.sort(items, new Comparator<ReportItem>() {
			@Override
			public int compare(ReportItem a, ReportItem b) {
				return a.getSortOrder().compareTo(b.getSortOrder());
			}
		});
		report.setItems(items);
		report.setAvgStatus(Math.ceil(average / items.size()));
		// Check if valid
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Report>> errors = validator.validate(report);
		List<String> errorList = new ArrayList<String>();
		// Hibernate validation errors
		for (ConstraintViolation<Report> error : errors) {
			errorList.add(error.getMessageTemplate());
		}
		// Return to form / view
		model.addAttribute("report", report);
		if (errorList.size() > 0) {
			logger.info("Report does not validate");
			model.addAttribute("errors", errorList);
			return reportCreate(model, request);
		} else {
			logger.info("Persisting report object");
			User currentUser = fetchUser();
			if (report.getDateCreated() == null) {
				report.setDateCreated(new Date());
				report.setCreatedBy(currentUser.getName());
			}
			report.setLastEdited(new Date());
			report.setLastEditedBy(currentUser.getName());
			report.setStatus(1);
			reportService.objectUpdate(report);
			return "redirect:/report/" + report.getName();
		}
	}

	@RequestMapping(value = "/report/{reportId}/update", method = RequestMethod.GET)
	public String reportUpdateView(@PathVariable("reportId") String name, ModelMap model, HttpServletRequest request) {
		logger.info("Received request to show specific report: " + name);
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		String dbQuery = reportService.getQuery(null, name, null, null);
		model.addAttribute("report", reportService.findOne(dbQuery));
		return getTemplate(model, request, "reportForm", config, start);
	}

	@RequestMapping(value = "/report/{reportId}", method = RequestMethod.GET)
	public String reportView(@PathVariable("reportId") String name, ModelMap model, HttpServletRequest request) {
		logger.info("Received request to show specific report: " + name);
		Long start = System.currentTimeMillis();
		Map<String, Object> config = configService.getConfig(request);
		String dbQuery = reportService.getQuery(null, name, null, null);
		model.addAttribute("report", reportService.findOne(dbQuery));
		return getTemplate(model, request, "reportView", config, start);
	}

}