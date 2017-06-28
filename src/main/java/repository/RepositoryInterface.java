package repository;

import java.util.List;

public interface RepositoryInterface<T> {
    T getByID(int id);

    void addNewEntity(T entity);

    void removeById(int id);

    void updateEntity(T entity);

    List<T> getAll();

}