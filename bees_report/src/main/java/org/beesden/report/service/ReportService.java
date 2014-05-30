package org.beesden.report.service;

import org.beesden.report.model.Report;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReportService extends Service<Report> {

	public ReportService() {
		super(Report.class.getName());
	}

}
