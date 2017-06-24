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

        int id = Integer.parseInt(request.getParameter("id"));

        FlightEntity flightEntity = repository.getByID(id);
        request.setAttribute("item", flightEntity);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        int id = Integer.parseInt(request.getParameter("id"));
        String flightNumber = request.getParameter("flightNumber");
        boolean directionType = Boolean.parseBoolean(request.getParameter("type"));
        String leavingFrom = request.getParameter("leavingFrom");
        String arrivalTo = request.getParameter("arrivalTo");
        LocalTime leavingTime = LocalTime.parse(request.getParameter("leavingTime"));
        LocalTime arrivalTime = LocalTime.parse(request.getParameter("arrivalTime"));

        FlightEntity flightEntity = new FlightEntity(id, flightNumber, directionType, leavingFrom, arrivalTo, arrivalTime, leavingTime);
        System.out.println(flightEntity);
        repository.update(flightEntity);
        response.sendRedirect("http://localhost:8080/info");
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