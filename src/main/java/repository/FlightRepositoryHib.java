package repository;

import entities.FlightHib;
import org.hibernate.Session;
import util.SessionFactoryUtil;

import java.util.List;

public class FlightRepositoryHib implements RepositoryInterface<FlightHib> {

    @Override
    public FlightHib getByID(final int id) {
        return (FlightHib) SessionFactoryUtil.transaction((Session session) -> session.get(FlightHib.class, id));
    }

    @Override
    public void addNewEntity(final FlightHib entity) {
        SessionFactoryUtil.transaction((Session session) -> session.save(entity));
    }

    @Override
    public void removeById(final int id) {
        SessionFactoryUtil.transaction((Session session) -> {
            session.delete(getByID(id));
            return null;
        });
    }

    @Override
    public void updateEntity(final FlightHib entity) {
        SessionFactoryUtil.transaction((Session session) -> {
            session.update(entity);
            return null;
        });
    }

    @Override
    public List<FlightHib> getAll() {
        return SessionFactoryUtil.transaction(
                (Session session) -> session.createQuery("from FlightHib").list());
    }
}