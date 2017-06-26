package repository;

import entities.FlightEntity;
import entities.FlightHib;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FlightRepositoryHibTest {
    private FlightRepositoryHib repositoryHib;
    private FlightHib flightHib = new FlightHib();

    {
        flightHib.setFlightNumber("TST-test-TST");
        flightHib.setDirectionType(FlightHib.ARRIVAL);

        flightHib.setLeavingTime(Timestamp.valueOf(LocalDateTime.now()));
        flightHib.setArrivalTime(Timestamp.valueOf(LocalDateTime.now().plusHours(4)));

        flightHib.setLeavingFrom("Leaving-Test");
        flightHib.setArrivalTo("Test-Arrival");
    }

    @Before
    public void init() throws Exception {
        repositoryHib = new FlightRepositoryHib();
    }

    @Test
    public void getByID() throws Exception {
        FlightHib flightHib = repositoryHib.getByID(3);
        assertNotNull(flightHib);
        assertEquals(flightHib.getFlightNumber(), "SF-111-IS");
    }

    @Test
    public void addNew() throws Exception {
// ADD>
        repositoryHib.addNew(flightHib);

        List<FlightHib> flightHibList = repositoryHib.getAll().stream().map(e -> ((FlightHib) e)).collect(Collectors.toList());
        FlightHib flightFound = flightHibList.stream().filter(e -> e.getFlightNumber().equals("TST-test-TST")).findFirst().get();
        assertEquals(flightFound.getFlightNumber(), "TST-test-TST");
        assertEquals(flightFound.getLeavingFrom(), "Leaving-Test");
        assertEquals(flightFound.getArrivalTo(), "Test-Arrival");
        assertNotNull(repositoryHib.getByID(getIdByFlightNumber()));
    }

    @Test
    public void remove() throws Exception {
        int id = getIdByFlightNumber();
        assertEquals(repositoryHib.getByID(id).getId(), id);
        repositoryHib.remove(getIdByFlightNumber());
        assertNull(repositoryHib.getByID(id));
    }

    private int getIdByFlightNumber() {
        List<FlightHib> flightHibList = repositoryHib.getAll().stream().map(e -> ((FlightHib) e)).collect(Collectors.toList());
        FlightHib flightFound = flightHibList.stream().filter(e -> e.getFlightNumber().equals("TST-test-TST")).findFirst().get();

        return flightFound.getId();
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void getAll() throws Exception {

    }

}