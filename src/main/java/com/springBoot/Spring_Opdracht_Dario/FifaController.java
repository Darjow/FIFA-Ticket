package com.springBoot.Spring_Opdracht_Dario;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Stadium;
import domain.WedstrijdTicket;
import service.VoetbalService;
import util.FormInput;
import validators.FormInputValidator;

@Controller
@RequestMapping("/fifa")
@SessionAttributes(names = {"stadium", "stadiumList"})
public class FifaController {
	
	@Autowired
	VoetbalService voetbalService;
	
	@Autowired
	FormInputValidator validator;
	
	@GetMapping
	public String showStadiumSelect(Model model) {
		model.addAttribute("stadiumList", voetbalService.getStadiumList());
		model.addAttribute("stadium", new Stadium());
		return "selectStadium";
	}
	
	@PostMapping
	public String stadiumOverview(@ModelAttribute Stadium stadium, Model model) {
		model.addAttribute("games", voetbalService.getWedstrijdenByStadium(stadium.getName()));
		return "stadiumOverview";
	}
	
	@GetMapping(value = "/{id}")
	public String showTicketOverview(@ModelAttribute Stadium stadium, @PathVariable String id,  Model model, RedirectAttributes redirect) {
		
		if(stadium.getName() == null) {
			return "redirect:/fifa";
		}
		if(voetbalService.getWedstrijd(id).uitverkocht()) {
			redirect.addFlashAttribute("uitverkocht", "De voetbalmatch is uitverkocht!");
			return "redirect:/fifa";
		}
		WedstrijdTicket x = voetbalService.getWedstrijd(id);
		if(x != null){
			model.addAttribute("ticket", voetbalService.getWedstrijd(id));
			model.addAttribute("formInput", new FormInput());
			return "ticketOverview";
		}
		return "redirect:/fifa";
	}
	
	
	@PostMapping(value = "/{id}")
	public String purchasedTickets(@Valid FormInput input, BindingResult result ,@PathVariable String id,Model model, RedirectAttributes redirect) {
		validator.validate(input, result);
		
		if(result.hasErrors()) {
			model.addAttribute("ticket", voetbalService.getWedstrijd(id));
			return "ticketOverview";
		}
		int bought = voetbalService.ticketsBestellen(id, input.getAantalTickets());
		redirect.addFlashAttribute("bought", String.format("%d ticket%s werd%s aangekocht ", bought, bought==1? "" : "s", bought==1? "" : "en" ));
		return "redirect:/fifa";
		
	}

}
