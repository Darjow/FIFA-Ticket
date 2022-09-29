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

    /*
     *
INSERT INTO `fifaworldcup`.`wedstrijd` (`dag`, `landen`, `uur`) VALUES ('26', 'België,Canada', '21');
INSERT INTO `fifaworldcup`.`wedstrijd` (`dag`, `landen`, `uur`) VALUES ('27', 'Brazilië,Zwitserland', '18');
INSERT INTO `fifaworldcup`.`wedstrijd` (`dag`, `landen`, `uur`) VALUES ('28', 'Marroko,Kroatië', '15');
INSERT INTO `fifaworldcup`.`wedstrijd` (`dag`, `landen`, `uur`) VALUES ('28', 'Spanje,Duitsland', '18');
INSERT INTO `fifaworldcup`.`wedstrijd` (`dag`, `landen`, `uur`) VALUES ('30', 'Frankrijk,Denemarken', '15');
INSERT INTO `fifaworldcup`.`wedstrijd` (`dag`, `landen`, `uur`) VALUES ('30', 'Argentinië,Mexico', '18');
INSERT INTO `fifaworldcup`.`wedstrijd` (`dag`, `landen`, `uur`) VALUES ('31', 'Engeland,USA', '18');
INSERT INTO `fifaworldcup`.`wedstrijd` (`dag`, `landen`, `uur`) VALUES ('31', 'Nederland,Qatar', '21');

INSERT INTO `fifaworldcup`.`stadium` (`name`) VALUES ('Al Thumama Stadium');
INSERT INTO `fifaworldcup`.`stadium` (`name`) VALUES ('Al Bayt Stadium');

INSERT INTO `fifaworldcup`.`wedstrijdticket` (`tickets`, `stadium_id`, `wedstrijd_id`) VALUES ('35', '1', '1');
INSERT INTO `fifaworldcup`.`wedstrijdticket` (`tickets`, `stadium_id`, `wedstrijd_id`) VALUES ('21', '1', '2');
INSERT INTO `fifaworldcup`.`wedstrijdticket` (`tickets`, `stadium_id`, `wedstrijd_id`) VALUES ('5', '1', '3');
INSERT INTO `fifaworldcup`.`wedstrijdticket` (`tickets`, `stadium_id`, `wedstrijd_id`) VALUES ('95', '2', '4');
INSERT INTO `fifaworldcup`.`wedstrijdticket` (`tickets`, `stadium_id`, `wedstrijd_id`) VALUES ('45', '2', '5');
INSERT INTO `fifaworldcup`.`wedstrijdticket` (`tickets`, `stadium_id`, `wedstrijd_id`) VALUES ('10', '1', '6');
INSERT INTO `fifaworldcup`.`wedstrijdticket` (`tickets`, `stadium_id`, `wedstrijd_id`) VALUES ('22', '1', '7');
INSERT INTO `fifaworldcup`.`wedstrijdticket` (`tickets`, `stadium_id`, `wedstrijd_id`) VALUES ('16', '2', '8');

 */

}
