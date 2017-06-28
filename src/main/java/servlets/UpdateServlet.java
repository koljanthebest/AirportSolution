package servlets;

import entities.FlightHib;
import entities.FlightJDBC;
import repository.FlightRepositoryHib;
import repository.FlightRepositoryJDBC;
import util.DbConnector;
import util.EntityUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalTime;

import static util.TimeUtils.toLocalTime;
import static util.TimeUtils.toTimeStamp;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private FlightRepositoryHib repository = new FlightRepositoryHib();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("'/update' -> 'DO POST' method:");

        int id = Integer.parseInt(request.getParameter("id"));

        FlightJDBC flight = EntityUtil.toJDBCEntity(repository.getByID(id));
        request.setAttribute("item", flight);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String flightNumber = request.getParameter("flightNumber");
        boolean directionType = Boolean.parseBoolean(request.getParameter("type"));
        String leavingFrom = request.getParameter("leavingFrom");
        String arrivalTo = request.getParameter("arrivalTo");
        String leavingTime = request.getParameter("leavingTime");
        String arrivalTime = request.getParameter("arrivalTime");
        FlightJDBC flightJDBC = new FlightJDBC(id, flightNumber, directionType, leavingFrom, arrivalTo, toLocalTime(leavingTime), toLocalTime(arrivalTime));

        FlightHib flight = EntityUtil.toHibEntity(flightJDBC);
        System.out.println(flight);
        repository.updateEntity(flight);
        response.sendRedirect("http://localhost:8080/info");
    }

}