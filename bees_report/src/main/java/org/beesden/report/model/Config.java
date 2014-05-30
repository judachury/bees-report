package org.beesden.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bees_config")
public class Config extends ModelDefault {
	private static final long serialVersionUID = 1L;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "type", length = 30)
	private String type;

	@Column(name = "value", columnDefinition = "TEXT")
	private String value;

	/* Getters and Setters */

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
