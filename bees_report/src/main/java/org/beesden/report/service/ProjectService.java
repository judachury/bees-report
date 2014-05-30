package org.beesden.report.service;

import org.beesden.report.model.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProjectService extends Service<Project> {

	public ProjectService() {
		super(Project.class.getName());
	}

}
