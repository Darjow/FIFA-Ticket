package repositories;

import java.util.List;

import domain.WedstrijdTicket;

public interface  WedstrijdTicketDao extends GenericDao<WedstrijdTicket> {

	public List<WedstrijdTicket> getWedstrijdenByStadium(int i);

}
