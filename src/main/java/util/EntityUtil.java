package util;


import entities.FlightHib;
import entities.FlightJDBC;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EntityUtil {
    public static FlightHib toHibEntity(final FlightJDBC flightJDBC) {
        FlightHib flightHib = new FlightHib();

        flightHib.setId(flightJDBC.getId());
        flightHib.setFlightNumber(flightJDBC.getFlightNumber());
        flightHib.setDirectionType((byte) (flightJDBC.getDirectionType() ? 1 : 0));
        flightHib.setLeavingFrom(flightJDBC.getLeavingFrom());
        flightHib.setArrivalTo(flightJDBC.getArrivalTo());
        flightHib.setLeavingTime(Timestamp.valueOf(LocalDateTime.now().with(flightJDBC.getLeavingTime())));
        flightHib.setArrivalTime(Timestamp.valueOf(LocalDateTime.now().with(flightJDBC.getArrivalTime())));
        return flightHib;
    }

    public static FlightJDBC toJDBCEntity(final FlightHib flightHib) {
        return new FlightJDBC(
                flightHib.getId(),
                flightHib.getFlightNumber(),
                flightHib.getDirectionType() == 1,
                flightHib.getLeavingFrom(),
                flightHib.getArrivalTo(),
                flightHib.getLeavingTime().toLocalDateTime().toLocalTime(),
                flightHib.getArrivalTime().toLocalDateTime().toLocalTime());
    }

    public static List<FlightJDBC> toJDBCEntityList(final List<FlightHib> flightHib) {
        return flightHib.stream().map(EntityUtil::toJDBCEntity).collect(Collectors.toList());
    }

    public static List<FlightHib> toHibEntityList(final List<FlightJDBC> flightHib) {
        return flightHib.stream().map(EntityUtil::toHibEntity).collect(Collectors.toList());
    }
}