import entities.FlightEntity;
import repository.FlightRepository;
import util.DbConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    static FlightEntity SF_KV = new FlightEntity(
            3,
            "SF-11-11-KV",
            FlightEntity.ARRIVAL,
            "San Francisco",
            "Kyiv", LocalDateTime.now(),
            LocalDateTime.now().plusHours(4));

    static FlightEntity KV_SF = new FlightEntity(
            4,
            "KV-7777-SF",
            FlightEntity.ARRIVAL,
            "Kyiv",
            "San Francisco", LocalDateTime.now(),
            LocalDateTime.now().plusHours(6)
    );

    public static void main(String[] args) throws SQLException {
        DbConnector dbConnector = DbConnector.getINSTANCE();
        Connection connection = dbConnector.getConnection();
        System.out.println(connection == null ?
                "NO CONNECTION !" : "CONNECTION SUCCESS !");
        FlightRepository flightRepository = new FlightRepository(connection);

// CREATE>
        //flightRepository.add(new FlightEntity());
        //flightRepository.add(SF_KV);
        //flightRepository.add(KV_SF);

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