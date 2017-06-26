package entities;

import javax.persistence.*;
import javax.print.attribute.standard.Finishings;
import java.sql.Timestamp;

@Entity
@Table(name = "flight", schema = "airport")
public class FlightHib {
    private int id;
    private String flightNumber;
    private byte directionType;
    private String leavingFrom;
    private String arrivalTo;
    private Timestamp leavingTime;
    private Timestamp arrivalTime;
    public static final byte LEAVING = 1, ARRIVAL = 0;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "flight_number")
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Basic
    @Column(name = "direction_type")
    public byte getDirectionType() {
        return directionType;
    }

    public void setDirectionType(byte directionType) {
        this.directionType = directionType;
    }

    @Basic
    @Column(name = "leaving_from")
    public String getLeavingFrom() {
        return leavingFrom;
    }

    public void setLeavingFrom(String leavingFrom) {
        this.leavingFrom = leavingFrom;
    }

    @Basic
    @Column(name = "arrival_to")
    public String getArrivalTo() {
        return arrivalTo;
    }

    public void setArrivalTo(String arrivalTo) {
        this.arrivalTo = arrivalTo;
    }

    @Basic
    @Column(name = "leaving_time")
    public Timestamp getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(Timestamp leavingTime) {
        this.leavingTime = leavingTime;
    }

    @Basic
    @Column(name = "arrival_time")
    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightHib flightHib = (FlightHib) o;

        if (id != flightHib.id) return false;
        if (directionType != flightHib.directionType) return false;
        if (flightNumber != null ? !flightNumber.equals(flightHib.flightNumber) : flightHib.flightNumber != null)
            return false;
        if (leavingFrom != null ? !leavingFrom.equals(flightHib.leavingFrom) : flightHib.leavingFrom != null)
            return false;
        if (arrivalTo != null ? !arrivalTo.equals(flightHib.arrivalTo) : flightHib.arrivalTo != null) return false;
        if (leavingTime != null ? !leavingTime.equals(flightHib.leavingTime) : flightHib.leavingTime != null)
            return false;
        if (arrivalTime != null ? !arrivalTime.equals(flightHib.arrivalTime) : flightHib.arrivalTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (flightNumber != null ? flightNumber.hashCode() : 0);
        result = 31 * result + (int) directionType;
        result = 31 * result + (leavingFrom != null ? leavingFrom.hashCode() : 0);
        result = 31 * result + (arrivalTo != null ? arrivalTo.hashCode() : 0);
        result = 31 * result + (leavingTime != null ? leavingTime.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nid = " + id +
                "\nflightNumber = " + flightNumber +
                "\ndirectionType = " + directionType +
                "\nleavingFrom = " + leavingFrom +
                "\narrivalTo = " + arrivalTo +
                "\nleavingTime = " + leavingTime +
                "\narrivalTime = " + arrivalTime +
                "\n";
    }
}