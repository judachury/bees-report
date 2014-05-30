package org.beesden.report.model;

import java.util.List;

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
@Table(name = "bees_report")
public class Report extends ModelDefault {
	private static final long serialVersionUID = 1L;

	@Column(name = "avgStatus")
	private Double avgStatus;

	@Column(name = "comments")
	private String comments;

	@OneToMany(orphanRemoval = true, mappedBy = "report", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("sortOrder")
	private List<ReportItem> items;

	@NotNull(message = "forms.project.required")
	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project project;

	// Getters and Setters

	public Double getAvgStatus() {
		return avgStatus;
	}

	public String getComments() {
		return comments;
	}

	public List<ReportItem> getItems() {
		return items;
	}

	public Project getProject() {
		return project;
	}

	public void setAvgStatus(Double avgStatus) {
		this.avgStatus = avgStatus;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setItems(List<ReportItem> items) {
		this.items = items;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
