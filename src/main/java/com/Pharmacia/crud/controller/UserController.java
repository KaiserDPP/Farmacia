package com.Pharmacia.crud.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Pharmacia.crud.model.Role;
import com.Pharmacia.crud.model.User;
import com.Pharmacia.crud.service.ProductService;
import com.Pharmacia.crud.service.RoleService;
import com.Pharmacia.crud.service.UserService;

import javassist.expr.NewArray;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	@Qualifier("servicesImpl")
	private UserService userService;

	@Autowired
	@Qualifier("servicesImpl")
	private ProductService productService;

	@Autowired
	@Qualifier("servicesImpl")
	private RoleService roleService;
	
	@GetMapping("/list")
	public String userForm(Locale locale, Model model) {

		model.addAttribute("user", new User());
		addAttributes(model, "Formulario Usuarios", "Formulario para añadir/modificar los usuarios");
		return "userForm";
	}

	@GetMapping("/edit")
	public String editUser(@RequestParam("username") String username, Model model) {
		User user = userService.getUser(username);
		model.addAttribute(user);
		addAttributes(model, "Formulario Usuarios", "Formulario para añadir/modificar los usuarios");
		return "userForm";
	}

	private void addAttributes(Model model, String ttl, String msj) {
		model.addAttribute("users", userService.listUsers());
		model.addAttribute("how_many", userService.numUsers());
		model.addAttribute("titulo", ttl);
		model.addAttribute("descripcion", msj);
		model.addAttribute("menu", "lista_usuarios");
	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model)
			throws SQLIntegrityConstraintViolationException {
		Boolean existe = false;
		if (result.hasErrors()) {
			model.addAttribute(user);
			addAttributes(model, "Formulario Usuarios", "Formulario para añadir/modificar los usuarios");
			return "userForm";
		}
		// try {
		userService.save(user);
//		} catch (Exception e) {
//			if (e.getCause() != null && e.getCause().getCause() instanceof ConstraintViolationException) {
//				ConstraintViolationException violation_exception = (ConstraintViolationException) e.getCause()
//						.getCause();				
//				e.printStackTrace();
//				existe = false;
//			} else {				
//				existe = true;
//			}
//			model.addAttribute(user);
//			addAttributes(model, "Formulario Usuarios", "Formulario para añadir/modificar los usuarios");
//
//			model.addAttribute("existe", existe);
//			return "userForm";
//		}
		model.addAttribute("existe", existe);
		return "redirect:/user/list";
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam("username") String username) {
		userService.deleteUser(username);
		return "redirect:/user/list";
	}

	@GetMapping("/loan_products")
	public String loanProductsUser(@RequestParam("username") String username, Model model) {
		User user = userService.getUser(username);
		model.addAttribute(user);
		model.addAttribute("productlist", productService.listProducts());
		model.addAttribute("titulo", "Medicamentos prestados a " + user.getName());
		model.addAttribute("descripcion", "Formulario para añadir/modificar medicamentos prestados por " + user.getName());
		// NO ES SAFE
		return "loanProducts";
	}

	@GetMapping("/ver")
	public String ver(@RequestParam("username") String username, Model model) {
		User user = userService.getUser(username);
		model.addAttribute(user);

		model.addAttribute("titulo", "Detalles acerca de " + user.getName());
		model.addAttribute("descripcion", "Tabla que muestra los detalles acerca de " + user.getName());
		return "ver";
	}

	@PostMapping("/confirm_loan")
	public String confirmLoan(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		Boolean existe = false;
		if (result.hasErrors()) {
			System.out.println("Pasando por la sección errores");
			model.addAttribute(user);
			addAttributes(model, "Formulario Usuarios", "Formulario para añadir/modificar los usuarios");
			return "userForm";
		}
		// Es aquí dónde deberiamos de controlar la entrada usuario desde el form
		// El form alquiler de libro del fichero llamado loanBooks.jsp nos presenta una
		// serie de datos confidenciales proveniente del usuario
		// como su email, nombre y sobre todo la contraseña!!!
		// aunque este cryptada, no debemos de proceder así. Porque no es seguro
		// MODIFICAR LO ANTES POSIBLE
		userService.loanProducts(user);

		model.addAttribute(user);
		addAttributes(model, "Formulario Usuarios", "Formulario para añadir/modificar los usuarios");

		model.addAttribute("existe", existe);
		return "redirect:/user/list";
	}
	@GetMapping("/role")
	public String role(@RequestParam("username") String username, Model model) {
		User user = userService.getUser(username);
		ArrayList<String> roles = new ArrayList<String>(Arrays.asList("ROLE_OWNER", "ROLE_USER", "ROLE_ADMIN"));
		model.addAttribute(user);
		model.addAttribute("roles",roles);
		model.addAttribute("users",userService.listUsers());
		model.addAttribute("role",new Role());
		model.addAttribute("titulo", "Gestionar permisos de " + user.getName());
		model.addAttribute("descripcion", "Tabla que muestra los detalles acerca de " + user.getName());
		return "userRole";
	}
	@PostMapping("/add_role")
	public String addRole(@ModelAttribute("role") @Valid Role role, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "redirect:/user/role";
		}
		
		roleService.save(role);

		
		return "redirect:/user/list";
}}
