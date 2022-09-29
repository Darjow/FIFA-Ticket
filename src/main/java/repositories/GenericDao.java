package repositories;

import java.util.List;

public interface GenericDao<T> {
    public <U> T get(U id);
    public T update(T object);
    public void delete(T object);
    public void insert(T object);
    public <U> boolean exists(U id);
	List<T> findAll();

}
