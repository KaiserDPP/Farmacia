package com.Pharmacia.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pharmacia.crud.dao.ProductDao;
import com.Pharmacia.crud.dao.CategoryDao;
import com.Pharmacia.crud.dao.UserDao;
import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.model.Category;
import com.Pharmacia.crud.model.User;
import com.Pharmacia.crud.other.Fecha;
import com.Pharmacia.crud.model.Role;
import com.Pharmacia.crud.dao.RoleDao;

@Service
public class ServicesImpl implements UserService, ProductService, CategoryService, RoleService, UserDetailsService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public void save(User user) {
		User copiaUser = null;
		// Si se trata de una creación de usuario
		if (userDao.get(user.getUsername()) == null) {
			copiaUser = new User();
			copiaUser.setUsername(user.getUsername());
			copiaUser.setEnabled(true);
			copiaUser.setUpdatedAt(Fecha.getTimeStamp("Europe/Madrid"));
			copiaUser.setCreatedAt(Fecha.getTimeStamp("Europe/Madrid"));
		} else {// El usuario ya existe
			copiaUser = userDao.get(user.getUsername());
			copiaUser.setUsername(user.getUsername());
			copiaUser.setEnabled(user.isEnabled());
			copiaUser.setUpdatedAt(Fecha.getTimeStamp("Europe/Madrid"));
		}
		copiaUser.setEmail(user.getEmail().toLowerCase());
		copiaUser.setSurname(user.getSurname().toUpperCase());
		copiaUser.setName(user.getName().substring(0, 1).toUpperCase()
				+ user.getName().substring(1, user.getName().length()).toLowerCase());
		// tenemos que encriptar la contraseña
		String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
		copiaUser.setPassword(encoded);
		copiaUser.setConfirmPassword(encoded);
		userDao.save(copiaUser);

	}
	@Override
	@Transactional
	public void save(Product product) {
		productDao.save(product);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> listUsers() {
		return userDao.list();
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> listProducts() {
		return productDao.list();
	}

	@Override
	@Transactional
	public void deleteUser(String username) {
		userDao.delete(username);
	}

	@Override
	@Transactional
	public void deleteProduct(long id) {
		productDao.delete(id);
	}

	@Override
	@Transactional
	public User getUser(String username) {
		User user = userDao.get(username);
		return user;
	}

//	@Override
//	@Transactional
//	public boolean confirmExisting(String email) {
//		boolean exist = userDao.confirmExisting(email);
//		return exist;
//	}

	@Override
	@Transactional
	public Product getProduct(long id) {
		Product product = productDao.get(id);
		return product;
	}

	@Override
	@Transactional
	public long numUsers() {
		return userDao.num();
	}

	@Override
	@Transactional
	public long numProducts() {
		return productDao.num();
	}

	@Override
	@Transactional
	public void save(Category category) {
		categoryDao.save(category);

	}

	@Override
	@Transactional(readOnly = true)
	public List<?> listCategories() {
		return categoryDao.list();
	}

	@Override
	@Transactional
	public void deleteCategory(long id) {
		categoryDao.delete(id);

	}

	@Override
	@Transactional
	public Category getCategory(long id) {
		Category category = categoryDao.get(id);
		return category;
	}

	@Override
	@Transactional
	public long numCategories() {
		return categoryDao.num();
	}

	@Override
	@Transactional
	public void loanProducts(User user) {

		userDao.loanProducts(user);

	}

	@Override
	@Transactional
	public void save(Role role) {
		roleDao.save(role);

	}
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.get(username);
		UserBuilder builder = null;
		if (user != null) {

			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());
			String[] authorities = user.getRoles().stream().map(a -> a.getAuthority()).toArray(String[]::new);

			builder.authorities(authorities);
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado.");
		}
		return builder.build();
	}

}
