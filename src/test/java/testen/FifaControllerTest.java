package testen;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.springBoot.Spring_Opdracht_Dario.Application;
import com.springBoot.Spring_Opdracht_Dario.FifaController;

import domain.Stadium;
import repositories.StadiumDao;
import repositories.WedstrijdTicketDao;
import service.VoetbalService;
import validators.FormInputValidator;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = Application.class)
@TestInstance(Lifecycle.PER_CLASS)
public class FifaControllerTest {

	
	/*
	 * mijn validation gebeurd niet, hierdoor geeft de testen verkeerde uitkomsten
	 * in het echt gebeurd de validatie wel en zouden de testen moeten slagen.
	 */
	
	
	@Autowired
	VoetbalService service;
	
	@Autowired
	WedstrijdTicketDao ticketDao;
	
	@Autowired
	StadiumDao stadiumDao;
	
	private FifaController controller;
    private MockMvc mockMvc;

    @BeforeAll
    public void before() {
        controller = new FifaController();
        mockMvc = standaloneSetup(controller).build();
        ReflectionTestUtils.setField(controller, "voetbalService", service);
        ReflectionTestUtils.setField(controller, "validator", new FormInputValidator());


    }

    @ParameterizedTest
	@MethodSource("addFixtureInvalid")
	public void buyTicketInvalidShowsFormAgain(int id, String email, String tickets, String code1, String code2) throws Exception{
    	mockMvc.perform(post("/fifa/" + id)
                .param(
                		"email", email,
                		"aantalTickets", tickets,
                		"voetbalCode1", code1,
                		"voetbalCode2", code2
                		))
      
    	
           .andExpect(view().name("ticketOverview"))
           .andExpect(model().hasErrors());

	}
    
    @ParameterizedTest
    @MethodSource("addFixtureValid")
    public void buyTicketValidRedirectToFifa(int id, String email, String tickets, String code1, String code2) throws Exception {
    	mockMvc.perform(post("/fifa/" + id)
                .param(
                		"email", email,
                		"aantalTickets", tickets,
                		"voetbalCode1", code1,
                		"voetbalCode2", code2
                		))
      
    	
           .andExpect(view().name("redirect:/fifa"))
           .andExpect(model().hasNoErrors());
    }
    
    @Test
    public void postStadiumSelectReturnsView() throws Exception{
    	mockMvc.perform(post("/fifa")
    			.param("name", "Al Bayt Stadium")
    			.sessionAttr("stadium", new Stadium()))
    	
    	.andExpect(view().name("stadiumOverview"))
    	.andExpect(model().attributeExists("stadium"))
    	.andExpect(model().attributeExists("games"));
    }
    
    /*@Test
    public void postBuyTicketsReturnBought() throws Exception {
    	int start = service.getWedstrijd("1").getTickets();
    	Stadium sessionAttribute = new Stadium();   sessionAttribute.setName("Al Thumama Stadium");
    	mockMvc.perform(post("/fifa")
    			.sessionAttr("stadium", sessionAttribute)
    			 .param(
                 		"email", "email@something.blabla",
                 		"aantalTickets", "5",
                 		"voetbalCode1", "5",
                 		"voetbalCode2", "10"
                 		))
    			
    	.andExpect(flash().attributeExists("bought"))
    	.andExpect(view().name("redirect:/fifa"));
    }
    */
    private static Stream<Arguments> addFixtureInvalid() {
		return Stream.of(
				Arguments.of(1,"k", "5","",""), 
				Arguments.of(2,"k@", "20", "7", "5"),
				Arguments.of(3,"","-5", "j", "0"), 
				Arguments.of(4,"k@","0", "1", "5"),
				Arguments.of(5,"k@","1","8","3"), 
				Arguments.of(6,"k@","3","20","1"),
				Arguments.of(7,"k","2", "0", "0"), 
				Arguments.of(8,"k@","6", "0", "-1")
			);
    }
				
		
	private static Stream<Arguments> addFixtureValid() {
			return Stream.of(
					Arguments.of(1,"k@a.com", "5","3","10"), 
					Arguments.of(2,"k@a.com", "1", "1", "3"),
					Arguments.of(3,"k@a.com","2", "3", "6"), 
					Arguments.of(4,"k@a.com","2", "8", "11"),
					Arguments.of(5,"k@a.com","10","1","3"),
					Arguments.of(6,"k@a.com","1","2","8"),
					Arguments.of(7,"k@a.com","25","1","3"),
					Arguments.of(8,"k@a.com","1","3","9")
				);
	}
	
		

}
