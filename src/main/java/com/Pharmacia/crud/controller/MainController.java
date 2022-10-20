package com.Pharmacia.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping({ "/", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("titulo", "BBDD PHARMACIA");
		String msj = "Base de datos  "
				+ "de medicamentos farmaceúticos que se integrarán "
				+ "	a partir de su categoría correspondiente. Se da la opción de asignar 3 tipos de accesos a la misma"
				+ "en función del nivel de responsabilidad del usario (ADMIN, OWNER y USER). <br/><br/>"
				+ "Todos los datos serán exportados a una base de datos en formato SQL."
				+ " y la inserción de los mismos se podrá hacer desde la base de datos o desde la consola.";
				
		mv.addObject("descripcion", "Proyecto SPRING con JAVA para gestionar una Base de Datos farmaceútica.");
		mv.addObject("descripcion_larga", msj);
		mv.addObject("menu", "index");
		mv.setViewName("index");

		return mv;
	}

	@RequestMapping("/contacto")
	public String contacto(Model model) {
		model.addAttribute("menu", "contacto");
		return "contacto";
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("titulo", "Página de login");
		mv.addObject("descripcion", "Presentación de las funciones login usando Java Framework Security");

		mv.setViewName("login");
		return mv;
	}

	@GetMapping("/forbidden")
	public ModelAndView forbidden() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("titulo", "Contenidos restringidos");
		mv.addObject("descripcion", "Ups! Por lo que parece, no puedes acceder a estos contenidos");

		mv.setViewName("forbidden");
		return mv;
	}
}
