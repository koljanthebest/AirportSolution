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
        System.out.println("'/info'  ->  'DO GET' method:");
        init();

        if (request.getParameter("action") != null) delete(request);
        List<FlightEntity> flightList = repository.getAll();

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("'/info'  ->  'DO POST' method:");
        init();
        if (request.getParameter("action").equals("skip")) doGet(request, response);
        if (request.getParameter("action").equals("filter-sort")) filterSort(request, response);
    }

    private void filterSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightEntity> flightList = repository.getAll();

        String typeFilter = request.getParameter("typeFilter");
        String sortFilter = request.getParameter("sortFilter");

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request) {
        if (!request.getParameter("action").equals("delete")) return;
        int id = Integer.parseInt(request.getParameter("id"));
        repository.remove(id);
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