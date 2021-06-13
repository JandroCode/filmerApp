package com.filmer.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filmer.entities.Rol;
import com.filmer.entities.Usuario;
import com.filmer.enums.RolNombre;
import com.filmer.service.RolService;
import com.filmer.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/registro")
	public String registrar() {
		return "registro";
	}
	
	
	@PostMapping("/save")
	public String saveUser(String username, String password,RedirectAttributes redirect, Model model) {
		
		if(usuarioService.existsByUsername(username)) {
			model.addAttribute("usuarioRepetido", "El usuario ya existe");
			return "registro";
		}
		
		if(username == "" || password == "") {
			model.addAttribute("camposVacios", "Los campos no pueden estar vacíos");
			return "registro";
		}
		
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(passwordEncoder.encode(password));
		
		Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
		//Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
		
		Set<Rol> roles = new HashSet<Rol>();
		roles.add(rolUser);
		//roles.add(rolAdmin);
		
		usuario.setRoles(roles);
		
		
		usuarioService.guardarUsuario(usuario);
		
		redirect.addFlashAttribute("usuarioRegistrado", "Registro completado, inicie sesión");
		
		
		return "redirect:/login";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
