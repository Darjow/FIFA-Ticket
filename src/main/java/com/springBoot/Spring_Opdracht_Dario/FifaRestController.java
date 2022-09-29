package com.springBoot.Spring_Opdracht_Dario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.VoetbalService;


@RestController
@RequestMapping(value = "/fifaDetail")
public class FifaRestController {
	
	  @Autowired
	  VoetbalService voetbalService;
	  
	  @GetMapping("/{id}")
	  public String[] getFifaDetail(@PathVariable String id){
		  try{
			  return voetbalService.getWedstrijd(id).getWedstrijd().getLanden();
		  }catch(Exception e) {
			  return null;
		  }
		  
	  }
	  

}
