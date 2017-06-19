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
import java.util.List;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {
    private DbConnector dbConnector;
    private Connection connection;
    private FlightRepository repository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("'/flight'  ->  'DO GET' method:");
        init();
        List<FlightEntity> flightList = repository.getAll();

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init/info");
        if (dbConnector != null) return;
        dbConnector = DbConnector.getINSTANCE();
        connection = dbConnector.getConnection();
        repository = new FlightRepository(connection);
    }
}