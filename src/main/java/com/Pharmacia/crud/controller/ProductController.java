package com.Pharmacia.crud.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Pharmacia.crud.model.Product;
import com.Pharmacia.crud.service.ProductService;
import com.Pharmacia.crud.service.CategoryService;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public String productForm(Locale locale, Model model) {
		model.addAttribute("product", new Product());
		addAttributes(model, "Formulario Medicamentos", "Formulario para añadir/modificar los medicamentos");
		return "productList";
	}

	@GetMapping("/edit")
	public String editProduct(@RequestParam("id") long id, Model model) {
		Product product = productService.getProduct(id);
		model.addAttribute(product);
		addAttributes(model, "Formulario Medicamentos", "Formulario para añadir/modificar los medicamentos");
		return "productList";
	}

	private void addAttributes(Model model, String ttl, String msj) {
		model.addAttribute("categories", categoryService.listCategories());
		model.addAttribute("products", productService.listProducts());
		model.addAttribute("how_many", productService.numProducts());
		model.addAttribute("titulo", ttl);
		model.addAttribute("descripcion", msj);
		model.addAttribute("menu", "lista_libros");
	}

	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute(product);
			addAttributes(model, "Formulario Medicamentos", "Formulario para añadir/modificar los medicamentos");
			// model.addAttribute("products", bookService.listBooks());
			return "productList";
		}

		productService.save(product);

		return "redirect:/product/list";
	}

	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("id") long id) {
		productService.deleteProduct(id);
		return "redirect:/product/list";
	}
}
