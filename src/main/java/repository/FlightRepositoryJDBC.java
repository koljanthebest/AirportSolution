package repository;

import entities.FlightJDBC;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryJDBC implements RepositoryInterface<FlightJDBC> {
    private Connection connection;

    public FlightRepositoryJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FlightJDBC getByID(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM flight WHERE id = ?;")) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return getFlightEntity(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addNewEntity(FlightJDBC flightJDBC) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO flight (flight_number, direction_type, leaving_from, arrival_to, leaving_time, arrival_time) VALUES(?, ?, ?, ?, ?, ?);")) {


            preparedStatement.setString(1, flightJDBC.getFlightNumber());
            preparedStatement.setBoolean(2, flightJDBC.getDirectionType());

            preparedStatement.setString(3, flightJDBC.getLeavingFrom());
            preparedStatement.setString(4, flightJDBC.getArrivalTo());

            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setTimestamp(5, Timestamp.valueOf(now.with(flightJDBC.getLeavingTime())));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(now.with(flightJDBC.getArrivalTime())));

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM flight WHERE id = ?;")) {
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEntity(FlightJDBC flightJDBC) {
        System.out.println(flightJDBC);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE flight SET flight_number = ?, direction_type = ?, leaving_from = ?, arrival_to = ?, leaving_time = ?, arrival_time = ? WHERE id = ?;")) {

            preparedStatement.setString(1, flightJDBC.getFlightNumber());
            preparedStatement.setBoolean(2, flightJDBC.getDirectionType());

            preparedStatement.setString(3, flightJDBC.getLeavingFrom());
            preparedStatement.setString(4, flightJDBC.getArrivalTo());

            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setTimestamp(5, Timestamp.valueOf(now.with(flightJDBC.getLeavingTime())));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(now.with(flightJDBC.getArrivalTime())));

            preparedStatement.setInt(7, flightJDBC.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FlightJDBC> getAll() {
        try (Statement statement = this.connection.createStatement();

             ResultSet resultSet = statement.executeQuery("SELECT * FROM flight;")) {
            List<FlightJDBC> entitiesList = new ArrayList<>();

            while (resultSet.next()) entitiesList.add(getFlightEntity(resultSet));

            return entitiesList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private FlightJDBC getFlightEntity(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String flightNumber = resultSet.getString("flight_number");
        boolean directionType = resultSet.getBoolean("direction_type");
        String leavingFrom = resultSet.getString("leaving_from");
        String arrivalTo = resultSet.getString("arrival_to");
        LocalTime leavingTime = resultSet.getTime("leaving_time").toLocalTime().truncatedTo(ChronoUnit.MINUTES);
        LocalTime arrivalTime = resultSet.getTime("arrival_time").toLocalTime().truncatedTo(ChronoUnit.MINUTES);

        return new FlightJDBC(id, flightNumber, directionType, leavingFrom, arrivalTo, leavingTime, arrivalTime);
    }
}