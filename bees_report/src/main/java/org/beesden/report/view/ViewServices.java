package org.beesden.report.view;

import org.apache.log4j.Logger;
import org.beesden.report.service.ConfigService;
import org.beesden.report.service.ProjectService;
import org.beesden.report.service.ReportService;
import org.beesden.report.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ViewServices {

	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	public ConfigService configService;

	@Autowired
	public ProjectService projectService;

	@Autowired
	public ReportService reportService;

	@Autowired
	public UserService userService;

}
