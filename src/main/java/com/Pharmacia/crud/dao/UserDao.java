package com.Pharmacia.crud.dao;

import java.util.List;

import com.Pharmacia.crud.model.User;

public interface UserDao {
	void save(User user);

	List<User> list();

	void delete(String username);

	User get(String username);

	long num();

	void loanProducts(User user);

	// boolean confirmExisting(String email);

}
