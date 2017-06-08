package repository;

import entities.FlightEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FlightRepository implements RepositoryInterface<FlightEntity> {
    private Connection connection;

    public FlightRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(FlightEntity flightEntity) throws SQLException {

        if (flightEntity.isEmptyEntity()) {
            connection.createStatement().execute("INSERT INTO flight() VALUES()"); // FOR DEFAULT VALUES
            return;
        }

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO flight (flight_number, direction_type, leaving_from, arrival_to, leaving_time, arrival_time) VALUES(?, ?, ?, ?, ?, ?);");

        preparedStatement.setString(1, flightEntity.getFlightNumber());
        preparedStatement.setBoolean(2, flightEntity.getDirectionType());

        preparedStatement.setString(3, flightEntity.getLeavingFrom());
        preparedStatement.setString(4, flightEntity.getArrivalTo());

        preparedStatement.setTimestamp(5, Timestamp.valueOf(flightEntity.getLeavingTime()));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(flightEntity.getArrivalTime()));

        preparedStatement.execute();
    }

    @Override
    public void remove(FlightEntity entity) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM flight WHERE id = ?;");
        preparedStatement.setInt(1, entity.getID());

        preparedStatement.execute();
    }

    @Override
    public void update(FlightEntity flightEntity) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE flight SET flight_number = ?, direction_type = ?, leaving_from = ?, arrival_to = ?, leaving_time = ?, arrival_time = ? WHERE id = ?;");
        preparedStatement.setString(1, flightEntity.getFlightNumber());
        preparedStatement.setBoolean(2, flightEntity.getDirectionType());

        preparedStatement.setString(3, flightEntity.getLeavingFrom());
        preparedStatement.setString(4, flightEntity.getArrivalTo());

        preparedStatement.setTimestamp(5, Timestamp.valueOf(flightEntity.getLeavingTime()));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(flightEntity.getArrivalTime()));

        preparedStatement.setInt(7, flightEntity.getID());

        preparedStatement.execute();
    }

    @Override
    public List<FlightEntity> getAll() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM flight;");
        List<FlightEntity> entitiesList = new ArrayList<>();

        while (resultSet.next()) {
            FlightEntity flightEntity = new FlightEntity();

            flightEntity.setID(resultSet.getInt("id"));
            flightEntity.setFlightNumber(resultSet.getString("flight_number"));
            flightEntity.setDirectionType(resultSet.getBoolean("direction_type"));

            flightEntity.setLeavingFrom(resultSet.getString("leaving_from"));
            flightEntity.setArrivalTo(resultSet.getString("arrival_to"));

            flightEntity.setLeavingTime(resultSet.getTimestamp("leaving_time").toLocalDateTime());
            flightEntity.setArrivalTime(resultSet.getTimestamp("arrival_time").toLocalDateTime());
            entitiesList.add(flightEntity);
        }
        return entitiesList;
    }
}