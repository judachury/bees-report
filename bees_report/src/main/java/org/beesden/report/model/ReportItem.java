package org.beesden.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bees_report_item")
public class ReportItem extends ModelDefault {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "reportId")
	private Report report;

	@Column(name = "sortOrder")
	private Integer sortOrder;

	@Column(name = "value")
	private String value;

	// Getters and Setters

	public Report getReport() {
		return report;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public String getValue() {
		return value;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setValue(String value) {
		this.value = value;
	}

}