package service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import domain.Stadium;

import domain.WedstrijdTicket;
import repositories.StadiumDao;
import repositories.WedstrijdTicketDao;



public class VoetbalServiceImpl implements VoetbalService{

	@Autowired
    private WedstrijdTicketDao ticketDao;
	
	@Autowired
    private StadiumDao stadiumDao;
    
    public VoetbalServiceImpl() {
    }
    
    public List<String> getStadiumList() {
        return stadiumDao.findAll().stream().map(e -> e.getName()).toList();
    }
    
    public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium) {
    	Stadium stadiumDB = stadiumDao.getStadiumByName(stadium);
    	if(stadiumDB == null) {
    		throw new IllegalArgumentException("Should not have happened.");
    	}
        return ticketDao.getWedstrijdenByStadium(stadiumDB.getId());
    }

    public WedstrijdTicket getWedstrijd(String id) {
        return ticketDao.get(Integer.parseInt(id));
    }

    public int ticketsBestellen(String id, int teBestellen) {
        WedstrijdTicket wedstrijdTicket = getWedstrijd(id);
        int bought =  wedstrijdTicket.ticketsKopen(teBestellen);
        ticketDao.update(wedstrijdTicket);
        
        return bought;
    }



}
