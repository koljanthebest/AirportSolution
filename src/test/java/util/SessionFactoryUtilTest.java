package util;

import entities.FlightHib;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SessionFactoryUtilTest {
    SessionFactory sessionFactory;

    @Before
    public void init() {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    @Test
    public void getSessionFactory() throws Exception {
        assertNotNull(sessionFactory);

        Session session = sessionFactory.openSession();
        assertNotNull(session);
// getByID>>
        FlightHib flightHib = (FlightHib) session.get(FlightHib.class, 9);
        assertEquals(flightHib.getId(), 9);
        assertEquals(flightHib.getFlightNumber(), "TR-798-ST");
        System.out.println(flightHib);
//getAll>>
        List<FlightHib> list = session.createQuery("from FlightHib").list();
        list.forEach(System.out::println);

        // updateEntity>>

        //delete>>
    }
}