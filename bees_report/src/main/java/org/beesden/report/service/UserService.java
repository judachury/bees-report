package org.beesden.report.service;

import org.beesden.report.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserService extends Service<User> {

	public UserService() {
		super(User.class.getName());
	}
}
