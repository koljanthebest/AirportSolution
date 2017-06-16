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

@WebServlet("/flight")
public class IndexServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("---<<SERVLET CLASS START>>---");

        DbConnector dbConnector = DbConnector.getINSTANCE();
        Connection connection = dbConnector.getConnection();
        FlightRepository repository = new FlightRepository(connection);
        List<FlightEntity> flightList = repository.getAll();


        request.setAttribute("name", "MIKE!");
          request.setAttribute("list", flightList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}