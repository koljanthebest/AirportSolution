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
import java.time.temporal.ChronoUnit;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private DbConnector dbConnector;
    private Connection connection;
    private FlightRepository repository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("'/update' -> 'DO POST' method:");

        init();

        String flightNumber = request.getParameter("flightNumber");

        FlightEntity flightEntity = repository.get(flightNumber);
        request.setAttribute("item", flightEntity);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        String flightNumber = request.getParameter("flight_number");
        boolean directionType = request.getParameter("type").equals("ПРИЛЁТ");
        String leavingFrom = request.getParameter("leaving_from");
        String arrivalTo = request.getParameter("arrival_to");
        LocalTime leavingTime = LocalTime.parse(request.getParameter("leaving_time"));
        LocalTime arrivalTime = LocalTime.parse(request.getParameter("arrival_time"));

        FlightEntity flightEntity = new FlightEntity(flightNumber, directionType, leavingFrom, arrivalTo, leavingTime, arrivalTime);
        repository.update(flightEntity);

        new InfoServlet().doGet(request, response);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init/update");
        if (dbConnector != null) return;
        dbConnector = DbConnector.getINSTANCE();
        connection = dbConnector.getConnection();
        repository = new FlightRepository(connection);
    }
}
