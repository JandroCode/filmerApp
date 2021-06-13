package com.filmer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filmer.entities.Actor;
import com.filmer.entities.Pelicula;
import com.filmer.service.IActorService;
import com.filmer.service.IPeliculasService;

@Controller
@RequestMapping("/actors")
@SessionAttributes("peliParaActor")
public class ActorController {
	
	
	  @Autowired private IActorService actorService;
	  @Autowired private IPeliculasService peliculaService;
	  
	  @GetMapping("/actors-form") public String actorsForm(Actor actor, Model
	  model, @ModelAttribute("peliParaActor")Pelicula pelicula) {
		  
		  model.addAttribute("actor", new Actor()); 
		  model.addAttribute("film", pelicula);
	  
		return "admin/actorsForm"; 
	  }
	  
	  @PostMapping("/save") public String saveActors(Actor actor,
	  RedirectAttributes redirect, Model
	  model, Pelicula pelicula ) {
		  
		  actorService.saveActor(actor);
		  redirect.addFlashAttribute("actorGuardado","Actor guardado");

		return "redirect:/actors/actors-form"; 
	}
	  
	  @GetMapping("/add-actores/{id}")
	  public String addActores(@PathVariable Long id, Model model) {
		 
		  Pelicula peli= peliculaService.peliculaPorId(id);
		 
		  model.addAttribute("actor", new Actor());
		  model.addAttribute("film", peli);
		  
		  return "admin/addActoresForm";
	  }
	  

	  
	  
	  
	  
	  
	  
	  
	
}
