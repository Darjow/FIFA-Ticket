package util;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



import validators.ValidEmail;

public class FormInput{

	@ValidEmail
	@NotEmpty
	private String email;
	
	@Min(value = 1, message = "{formInput.aantalTickets.min}")
	@Max(value = 25, message = "{formInput.aantalTickets.max}")
	@NotNull
	private int aantalTickets = 1;
	
	@NotNull
	private int voetbalCode1 = 10;
	
	@NotNull
	private int voetbalCode2 = 20;
		
	public FormInput() {}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public int getAantalTickets() {	return aantalTickets;}
	public void setAantalTickets(int aantalTickets) {this.aantalTickets = aantalTickets;}
	public int getVoetbalCode1() {return voetbalCode1;}
	public void setVoetbalCode1(int voetbalCode1) {this.voetbalCode1 = voetbalCode1;}
	public int getVoetbalCode2() {return voetbalCode2;}
	public void setVoetbalCode2(int voetbalCode2) {	this.voetbalCode2 = voetbalCode2;};

	
	
	
}
