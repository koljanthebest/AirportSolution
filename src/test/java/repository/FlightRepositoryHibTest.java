package repository;

import entities.FlightHib;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class FlightRepositoryHibTest {
    private static FlightRepositoryHib repositoryHib = new FlightRepositoryHib();
    private FlightHib flightEntityTest = new FlightHib();
    private static int dbSize = -1;

    @BeforeClass
    public static void beforeAll() {
        System.out.println("beforeClass:\nDATA ROWS SIZE = " + (dbSize = repositoryHib.getAll().size()));
    }

    // default entity init>>
    @Before
    public void initDefaultEntity() {
        flightEntityTest.setFlightNumber("TST-test-TST");
        flightEntityTest.setDirectionType(FlightHib.ARRIVAL);

        flightEntityTest.setLeavingTime(Timestamp.valueOf(LocalDateTime.now()));
        flightEntityTest.setArrivalTime(Timestamp.valueOf(LocalDateTime.now().plusHours(4)));

        flightEntityTest.setLeavingFrom("Leaving-Test");
        flightEntityTest.setArrivalTo("Test-Arrival");
    }

    @After
    public void dbSizeCheck() {
        int newSize = repositoryHib.getAll().size();
        System.out.println("\nDATA ROWS SIZE WAS = " + dbSize);
        System.out.println("DATA ROWS SIZE NOW = " + newSize);
        assertEquals(dbSize, newSize);
    }

    @Test
    public void getByID() throws Exception {
        FlightHib flightHib = repositoryHib.getByID(3);
        assertNotNull(flightHib);
        assertEquals(flightHib.getFlightNumber(), "SF-111-IS");
    }

    @Test
    public void addNew_Delete() throws Exception {
// ADD default test entity>
        repositoryHib.addNewEntity(flightEntityTest);

        List<FlightHib> flightHibList = repositoryHib.getAll();
        FlightHib flightFound = flightHibList.stream().filter(e -> e.getFlightNumber().equals("TST-test-TST")).findFirst().get();
        assertEquals(flightFound.getFlightNumber(), "TST-test-TST");
        assertEquals(flightFound.getLeavingFrom(), "Leaving-Test");
        assertEquals(flightFound.getArrivalTo(), "Test-Arrival");
        int id = getIdByFlightNumber(flightFound.getFlightNumber());
        assertNotNull(repositoryHib.getByID(id));

//removeById>>
        assertEquals(repositoryHib.getByID(id).getId(), id);
        repositoryHib.removeById(id);
        assertNull(repositoryHib.getByID(id));
    }

    @Test
    public void update() throws Exception {
//add default test entity>>
        repositoryHib.addNewEntity(flightEntityTest);
        int id = getIdByFlightNumber(flightEntityTest.getFlightNumber());// default entity id <
//set new fields>>
        flightEntityTest.setFlightNumber("UPDATED");
        flightEntityTest.setArrivalTo("UPDATED_ArrivalTo");
        flightEntityTest.setLeavingFrom("UPDATED_LeavingFrom");
        flightEntityTest.setId(id);
//updateEntity>>
        repositoryHib.updateEntity(flightEntityTest);
//test it>>
        FlightHib testEntity = repositoryHib.getByID(id);
        assertEquals("UPDATED", testEntity.getFlightNumber());
        assertEquals("UPDATED_ArrivalTo", testEntity.getArrivalTo());
        assertEquals("UPDATED_LeavingFrom", testEntity.getLeavingFrom());
//removeById>>
        assertEquals(repositoryHib.getByID(id).getId(), id);
        repositoryHib.removeById(getIdByFlightNumber(testEntity.getFlightNumber()));
        assertNull(repositoryHib.getByID(id));
    }

    @Test
    public void getAll() throws Exception {
        List<FlightHib> allList = repositoryHib.getAll();
        assertNotNull(allList);
        assertTrue(allList instanceof List);
    }

    private int getIdByFlightNumber(final String flightNumber) {
        return repositoryHib.getAll()
                .stream()
                .filter(e -> e.getFlightNumber().equals(flightNumber))
                .findFirst().get().getId();
    }
}