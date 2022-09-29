package repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import domain.Stadium;

@Repository("stadium_dao")
public class StadiumDaoJpa extends GenericDaoJpa<Stadium> implements StadiumDao{

	public StadiumDaoJpa(Class<Stadium> type) {
		super(type);

	}
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Stadium getStadiumByName(String name) {
		return em.createNamedQuery("Stadium.getStadiumByName", Stadium.class)
				.setParameter("name", name)
				.getSingleResult();
	}

}
