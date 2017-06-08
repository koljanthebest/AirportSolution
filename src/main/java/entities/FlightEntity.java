package entities;

import java.time.LocalDateTime;

public class FlightEntity {  // POJO
    private Integer ID;
    private String flightNumber;
    private boolean directionType;
    private String leavingFrom;
    private String arrivalTo;
    private LocalDateTime leavingTime;
    private LocalDateTime arrivalTime;
    public static final boolean LEAVING = false, ARRIVAL = true;

    public FlightEntity() {
    }

    public FlightEntity(Integer id,
                        String flightNumber,
                        boolean directionType,
                        String leavingFrom,
                        String arrivalTo,
                        LocalDateTime arrivalTime,
                        LocalDateTime leavingTime) {
        this.ID = id;
        this.flightNumber = flightNumber;
        this.leavingFrom = leavingFrom;
        this.arrivalTo = arrivalTo;
        this.arrivalTime = arrivalTime;
        this.leavingTime = leavingTime;
        this.directionType = directionType;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getLeavingFrom() {
        return leavingFrom;
    }

    public void setLeavingFrom(String leavingFrom) {
        this.leavingFrom = leavingFrom;
    }

    public String getArrivalTo() {
        return arrivalTo;
    }

    public void setArrivalTo(String arrivalTo) {
        this.arrivalTo = arrivalTo;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(LocalDateTime leavingTime) {
        this.leavingTime = leavingTime;
    }

    public boolean getDirectionType() {
        return directionType;
    }

    public void setDirectionType(boolean directionType) {
        this.directionType = directionType;
    }

    public boolean isEmptyEntity() {
        return flightNumber == null && leavingFrom == null && arrivalTo == null;
    }

    @Override
    public String toString() {
        return
                "\nID = " + ID +
                        ", flightNumber = " + flightNumber +
                        "\ndirectionType = " + directionType +
                        "\nleavingFrom = " + leavingFrom +
                        "\narrivalTo = " + arrivalTo +
                        "\nleavingTime = " + leavingTime +
                        "\narrivalTime = " + arrivalTime +
                        "\n";
    }
}