package org.beesden.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "bees_user")
public class User extends ModelDefault {
	private static final long serialVersionUID = 1L;

	@Column(name = "authority")
	private Integer authority;

	@NotNull(message = "forms.email.required")
	@Email(message = "forms.email.required")
	@Column(name = "email", length = 35)
	private String email;

	@NotNull(message = "forms.firstname.required")
	@Column(name = "firstname", length = 150)
	private String firstname;

	@NotNull(message = "forms.password.required")
	@Length(min = 6, message = "forms.password.minlength")
	@Column(name = "password")
	private String password;

	@NotNull(message = "forms.surname.required")
	@Column(name = "surname", length = 150)
	private String surname;

	// Getters and Setters

	public Integer getAuthority() {
		return authority;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getPassword() {
		return password;
	}

	public String getSurname() {
		return surname;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
