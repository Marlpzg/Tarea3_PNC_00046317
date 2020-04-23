package com.uca.capas.Tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/validar")
	public ModelAndView parametros3(@RequestParam(value="name") String nom, 
			@RequestParam(value="last") String ape,
			@RequestParam(value="birthdate") String bdstr,
			@RequestParam(value="birthplace") String bp,
			@RequestParam(value="school") String school,
			@RequestParam(value="phone") String phone,
			@RequestParam(value="cell") String cell) {
		
		List<String> errors = new ArrayList<>();
		
		
		if(nom.length() < 1 || nom.length() > 25) {
			errors.add("El nombre debe tener entre 1 y 25 caracteres.");
		}

		if(ape.length() < 1 || ape.length() > 25) {
			errors.add("El apellido debe tener entre 1 y 25 caracteres.");
		}
		
		try {
			Date bd = new SimpleDateFormat("yyyy-MM-dd").parse(bdstr);
			Date minDate = new SimpleDateFormat("yyyy-MM-dd").parse("2002-12-31");
			if(!bd.after(minDate)) {
				errors.add("La fecha minima permitida es el 1 de enero de 2003.");
			}
		} catch (ParseException e) {
			errors.add("Debe ingresar una fecha valida.");
		}
		
		if(bp.length() < 1 || bp.length() > 25) {
			errors.add("El lugar de nacimiento debe tener entre 1 y 25 caracteres.");
		}
		
		if(school.length() < 1 || school.length() > 25) {
			errors.add("El instituto o colegio de procedencia debe tener entre 1 y 100 caracteres.");
		}
		
		try {
			Integer.parseInt(phone);
			if(phone.length() != 8) {
				errors.add("El teléfono fijo debe tener exactamente 8 dígitos.");
			}
		}catch(NumberFormatException e) {
			errors.add("Ingrese un teléfono fijo válido.");
		}
		
		try {
			Integer.parseInt(cell);
			if(cell.length() != 8) {
				errors.add("El teléfono móvil debe tener exactamente 8 dígitos.");
			}
		}catch(NumberFormatException e) {
			errors.add("Ingrese un teléfono móvil válido.");
		}
		
		
		ModelAndView mav = new ModelAndView();
		
		if(!errors.isEmpty()) {
			mav.addObject("errors", errors);
			mav.setViewName("failure");
		}else {
			mav.setViewName("success");
		}
		
		return mav;
	
	}

}
