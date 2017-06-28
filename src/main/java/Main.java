import entities.FlightJDBC;
import repository.FlightRepositoryJDBC;
import util.DbConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {/*
    static FlightJDBC SF_KV = new FlightJDBC(
            "SF-11-11-KV",
            FlightJDBC.ARRIVAL,
            "San Francisco",
            "Kyiv",
            LocalTime.now(),
            LocalTime.now().plusHours(1));

    static FlightJDBC KV_SF = new FlightJDBC(
            "KV-7777-SF",
            FlightJDBC.LEAVING,
            "Kyiv",
            "San Francisco",
            LocalTime.now(),
            LocalTime.now().plusHours(3));

*/
    public static void main(String[] args) throws SQLException {
        DbConnector dbConnector = DbConnector.getINSTANCE();
        Connection connection = dbConnector.getConnection();
        System.out.println(connection == null ? "NO CONNECTION !" : "CONNECTION SUCCESS !\n");

        FlightRepositoryJDBC flightRepositoryJDBC = new FlightRepositoryJDBC(connection);

// CREATE>
//        flightRepositoryJDBC.addNewEntity(new FlightJDBC());
//        flightRepositoryJDBC.addNewEntity(SF_KV);
//        flightRepositoryJDBC.addNewEntity(KV_SF);

// READ>
        List<FlightJDBC> all = flightRepositoryJDBC.getAll();
        all.forEach(System.out::println);

// UPDATE>
        // SF_KV.setLeavingFrom("Lviv");
        // SF_KV.setArrivalTo("Ierusalem");
        // flightRepositoryJDBC.updateEntity(SF_KV);

// DELETE>
        //flightRepositoryJDBC.removeById(1);

        System.out.println("\t ALL  SUCCESSFUL !");
    }
}