package repository;

import entities.FlightEntity;

import java.io.IOError;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository implements RepositoryInterface<FlightEntity> {
    private Connection connection;

    public FlightRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FlightEntity get(int id) {
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
    public void add(FlightEntity flightEntity) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO flight (flight_number, direction_type, leaving_from, arrival_to, leaving_time, arrival_time) VALUES(?, ?, ?, ?, ?, ?);")) {


            preparedStatement.setString(1, flightEntity.getFlightNumber());
            preparedStatement.setBoolean(2, flightEntity.getDirectionType());

            preparedStatement.setString(3, flightEntity.getLeavingFrom());
            preparedStatement.setString(4, flightEntity.getArrivalTo());

            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setTimestamp(5, Timestamp.valueOf(now.with(flightEntity.getLeavingTime())));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(now.with(flightEntity.getArrivalTime())));

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
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
    public void update(FlightEntity flightEntity) {
        System.out.println(flightEntity);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE flight SET flight_number = ?, direction_type = ?, leaving_from = ?, arrival_to = ?, leaving_time = ?, arrival_time = ? WHERE id = ?;")) {

            preparedStatement.setString(1, flightEntity.getFlightNumber());
            preparedStatement.setBoolean(2, flightEntity.getDirectionType());

            preparedStatement.setString(3, flightEntity.getLeavingFrom());
            preparedStatement.setString(4, flightEntity.getArrivalTo());

            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setTimestamp(5, Timestamp.valueOf(now.with(flightEntity.getLeavingTime())));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(now.with(flightEntity.getArrivalTime())));

            preparedStatement.setInt(7, flightEntity.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FlightEntity> getAll() {
        try (Statement statement = this.connection.createStatement();

             ResultSet resultSet = statement.executeQuery("SELECT * FROM flight;")) {
            List<FlightEntity> entitiesList = new ArrayList<>();

            while (resultSet.next()) entitiesList.add(getFlightEntity(resultSet));

            return entitiesList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private FlightEntity getFlightEntity(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String flightNumber = resultSet.getString("flight_number");
        boolean directionType = resultSet.getBoolean("direction_type");
        String leavingFrom = resultSet.getString("leaving_from");
        String arrivalTo = resultSet.getString("arrival_to");
        LocalTime leavingTime = resultSet.getTime("leaving_time").toLocalTime().truncatedTo(ChronoUnit.MINUTES);
        LocalTime arrivalTime = resultSet.getTime("arrival_time").toLocalTime().truncatedTo(ChronoUnit.MINUTES);

        return new FlightEntity(id, flightNumber, directionType, leavingFrom, arrivalTo, leavingTime, arrivalTime);
    }
}