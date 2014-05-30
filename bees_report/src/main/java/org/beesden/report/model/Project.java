package org.beesden.report.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bees_project")
public class Project extends ModelDefault {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "forms.duedate.required")
	@Column(name = "dueDate")
	private Date dueDate;

	@ManyToOne
	@NotNull(message = "forms.developer.required")
	@JoinColumn(name = "leadDeveloperId")
	private User leadDeveloper;

	@ManyToOne
	@NotNull(message = "forms.manager.required")
	@JoinColumn(name = "projectManagerId")
	private User projectManager;

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("name desc")
	private Set<Report> reports;

	@Column(name = "summary")
	private String summary;

	@NotNull(message = "forms.testdate.required")
	@Column(name = "testDate")
	private Date testDate;

	// Getters and Setters

	public Date getDueDate() {
		return dueDate;
	}

	public User getLeadDeveloper() {
		return leadDeveloper;
	}

	public User getProjectManager() {
		return projectManager;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public String getSummary() {
		return summary;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setLeadDeveloper(User leadDeveloper) {
		this.leadDeveloper = leadDeveloper;
	}

	public void setProjectManager(User projectManager) {
		this.projectManager = projectManager;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
}
