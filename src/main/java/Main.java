import entities.FlightEntity;
import repository.FlightRepository;
import util.DbConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {/*
    static FlightEntity SF_KV = new FlightEntity(
            "SF-11-11-KV",
            FlightEntity.ARRIVAL,
            "San Francisco",
            "Kyiv",
            LocalTime.now(),
            LocalTime.now().plusHours(1));

    static FlightEntity KV_SF = new FlightEntity(
            "KV-7777-SF",
            FlightEntity.LEAVING,
            "Kyiv",
            "San Francisco",
            LocalTime.now(),
            LocalTime.now().plusHours(3));

*/
    public static void main(String[] args) throws SQLException {
        DbConnector dbConnector = DbConnector.getINSTANCE();
        Connection connection = dbConnector.getConnection();
        System.out.println(connection == null ? "NO CONNECTION !" : "CONNECTION SUCCESS !\n");

        FlightRepository flightRepository = new FlightRepository(connection);

// CREATE>
//        flightRepository.add(new FlightEntity());
//        flightRepository.add(SF_KV);
//        flightRepository.add(KV_SF);

// READ>
        List<FlightEntity> all = flightRepository.getAll();
        all.forEach(System.out::println);

// UPDATE>
        // SF_KV.setLeavingFrom("Lviv");
        // SF_KV.setArrivalTo("Ierusalem");
        // flightRepository.update(SF_KV);

// DELETE>
        //flightRepository.remove(1);

        System.out.println("\t ALL  SUCCESSFUL !");
    }
}