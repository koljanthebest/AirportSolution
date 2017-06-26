package repository;

import java.util.List;

public interface RepositoryInterface<T> {
    T getByID(int id);

    void addNew(T entity);

    void remove(int id);

    void update(T entity);

    List<T> getAll();

}