package com.Pharmacia.crud.service;

import java.util.List;

import com.Pharmacia.crud.model.User;

public interface UserService {
	void save(User user);

	List<User> listUsers();

	void deleteUser(String username);

	User getUser(String username);

	long numUsers();

	void loanProducts(User user);

	// boolean confirmExisting(String email);

}
