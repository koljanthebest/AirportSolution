package repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryInterface<T> {
    T get(String id) throws SQLException;

    void add(T entity) throws SQLException;

    void remove(String id) throws SQLException;

    void update(T entity) throws SQLException;

    List<T> getAll() throws SQLException;

}