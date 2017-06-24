package servlets;

import entities.FlightEntity;
import repository.FlightRepository;
import util.DbConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalTime;

@WebServlet("/addNew")
public class AddServlet extends HttpServlet {
    private DbConnector dbConnector;
    private Connection connection;
    private FlightRepository repository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/addNew.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String flightNumber = request.getParameter("flightNumber");
        boolean directionType = Boolean.parseBoolean(request.getParameter("type"));
        String leavingFrom = request.getParameter("leavingFrom");
        String arrivalTo = request.getParameter("arrivalTo");
        LocalTime leavingTime = LocalTime.parse(request.getParameter("leavingTime"));
        LocalTime arrivalTime = LocalTime.parse(request.getParameter("arrivalTime"));

        FlightEntity flightEntity = new FlightEntity(-1, flightNumber, directionType, leavingFrom, arrivalTo, arrivalTime, leavingTime);
        System.out.println(flightEntity);
        repository.addNew(flightEntity);
        response.sendRedirect("http://localhost:8080/info");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init/addNew");
        if (dbConnector != null) return;
        dbConnector = DbConnector.getINSTANCE();
        connection = dbConnector.getConnection();
        repository = new FlightRepository(connection);
    }
}
