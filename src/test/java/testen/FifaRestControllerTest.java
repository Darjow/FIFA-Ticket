package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springBoot.Spring_Opdracht_Dario.FifaRestController;

import service.VoetbalService;

@ExtendWith(MockitoExtension.class)
public class FifaRestControllerTest {

	
	
	@InjectMocks
	FifaRestController controller;
	
	@Mock
	VoetbalService service;
	
	static Map<String,String[]> mockData = new HashMap<>();
	
	static {
		mockData.put("1",new String[]{"België", "Canada"});
		mockData.put("2",new String[]{"Brazilië", "Zwitserland"});
		mockData.put("3",new String[]{"Marroko", "Kroatië"});
		mockData.put("4",new String[]{"Spanje", "Duitsland"});

	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {"1","2","3","4"})
	public void getDetailsByIdReturnsCountry(String id) {
		String[] countries1 = controller.getFifaDetail(id);
		String[] countries2 = service.getWedstrijd(id).getWedstrijd().getLanden();
		String expected = mockData.get(id)[0] + "," + mockData.get(id)[1];
		
		assertEquals(countries1, expected);
		assertEquals(countries2, expected);
		
	}
	

	
}
