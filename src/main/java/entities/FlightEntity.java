package entities;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;

public class FlightEntity {  // POJO
    // private Integer ID;
    final private String flightNumber;
    final private boolean directionType;
    final private String leavingFrom;
    final private String arrivalTo;
    final private LocalTime leavingTime;
    final private LocalTime arrivalTime;
    public static final boolean LEAVING = false, ARRIVAL = true;

    public FlightEntity(/*Integer id,*/
                        String flightNumber,
                        boolean directionType,
                        String leavingFrom,
                        String arrivalTo,
                        LocalTime arrivalTime,
                        LocalTime leavingTime) {
        //this.ID = id;
        this.flightNumber = flightNumber;
        this.directionType = directionType;

        this.leavingFrom = leavingFrom;
        this.arrivalTo = arrivalTo;

        this.arrivalTime = arrivalTime.truncatedTo(ChronoUnit.MINUTES);
        this.leavingTime = leavingTime.truncatedTo(ChronoUnit.MINUTES);
        ;

    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public boolean getDirectionType() {
        return directionType;
    }

    public String getLeavingFrom() {
        return leavingFrom;
    }

    public String getArrivalTo() {
        return arrivalTo;
    }

    public LocalTime getLeavingTime() {
        return LocalTime.from(leavingTime);
    }

    public LocalTime getArrivalTime() {
        return LocalTime.from(arrivalTime);
    }

    @Override
    public String toString() {
        return
               /* "\nID = " + ID +*/
                "\nflightNumber = " + flightNumber +
                        "\ndirectionType = " + directionType +
                        "\nleavingFrom = " + leavingFrom +
                        "\narrivalTo = " + arrivalTo +
                        "\nleavingTime = " + leavingTime +
                        "\narrivalTime = " + arrivalTime +
                        "\n";
    }
}