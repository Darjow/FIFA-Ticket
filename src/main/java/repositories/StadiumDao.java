package repositories;

import domain.Stadium;

public interface StadiumDao extends GenericDao<Stadium> {

	public Stadium getStadiumByName(String name);
}
