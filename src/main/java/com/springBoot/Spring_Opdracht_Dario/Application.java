package com.springBoot.Spring_Opdracht_Dario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import domain.Stadium;
import domain.WedstrijdTicket;
import repositories.StadiumDao;
import repositories.StadiumDaoJpa;
import repositories.WedstrijdTicketDao;
import repositories.WedstrijdTicketDaoJpa;
import service.VoetbalService;
import service.VoetbalServiceImpl;
import validators.FormInputValidator;

@SpringBootApplication
public class Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("resources/css/");
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/403").setViewName("403");
	}


	@Bean
	VoetbalService voetbalService() {
		return new VoetbalServiceImpl();
	}
	
	@Bean FormInputValidator formInputValidator() {
		return new FormInputValidator();
	}
	
	
	@Bean
	public WedstrijdTicketDao ticketDao() {
		return new WedstrijdTicketDaoJpa(WedstrijdTicket.class);
	}

	@Bean
	public StadiumDao stadiumDao() {
		return new StadiumDaoJpa(Stadium.class);
	}
}

















