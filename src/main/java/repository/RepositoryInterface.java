package repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryInterface<T> {
    T get(int id);

    void add(T entity);

    void remove(int id);

    void update(T entity);

    List<T> getAll();

}