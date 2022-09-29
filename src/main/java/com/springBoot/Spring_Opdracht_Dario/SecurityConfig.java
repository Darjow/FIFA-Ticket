package com.springBoot.Spring_Opdracht_Dario;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	          .withUser("user").password(passwordEncoder().encode("12345678")).roles("USER")
	          		.and()
	          .withUser("admin").password(passwordEncoder().encode("12345678")).roles("ADMIN", "USER");
	    }
 

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.authorizeRequests()
    		.antMatchers("/403").permitAll()
			.antMatchers("/*").hasRole("USER")
			.antMatchers("/fifa/*").hasRole("ADMIN");
            
    	http.formLogin()
    		.defaultSuccessUrl("/fifa",true)
    		.permitAll();

    	http.exceptionHandling()
    		.accessDeniedPage("/403");
    	
    	http.logout()
    		.logoutSuccessUrl("/login")
    		.permitAll();
    	
    	http.csrf();
    
}

    	


    }