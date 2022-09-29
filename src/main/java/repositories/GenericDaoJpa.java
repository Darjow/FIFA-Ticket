package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericDaoJpa<T> implements GenericDao<T> {

	
	private EntityManager em;

	protected final Class<T> type;

	public GenericDaoJpa(Class<T> type) {
		this.type = type;
	}

   @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return em.createQuery("select entity from " + type.getName() + " entity", type).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public <U> T get(U id) {
		return em.find(type, id);
	}

	@Override
	@Transactional(readOnly = false)
	public T update(T object) {
		T placeholder = null;
		placeholder = em.merge(object);

		return placeholder;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(T object) {
		em.remove(em.merge(object));
	}

	@Override
	@Transactional(readOnly = false)
	public void insert(T object) {
		em.persist(object);
	}

	@Override
	@Transactional(readOnly = false)
	public <U> boolean exists(U id) {
		T entity = em.find(type, id);
		return entity != null;
	}

}
