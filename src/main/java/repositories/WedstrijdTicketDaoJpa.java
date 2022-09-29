package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import domain.WedstrijdTicket;

@Repository("stadium_dao")
public class WedstrijdTicketDaoJpa extends GenericDaoJpa<WedstrijdTicket> implements WedstrijdTicketDao {

	public WedstrijdTicketDaoJpa(Class<WedstrijdTicket> type) {
		super(type);
	}

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<WedstrijdTicket> getWedstrijdenByStadium(int stadium_id) {
		return em.createNamedQuery("WedstrijdTicket.getWedstrijdenByStadium", WedstrijdTicket.class)
			.setParameter("id", stadium_id)
			.getResultList();
	}

}
