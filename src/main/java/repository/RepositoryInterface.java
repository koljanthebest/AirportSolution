package repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryInterface<T> {

    void add(T entity) throws SQLException;

    void remove(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    List<T> getAll() throws SQLException;

}