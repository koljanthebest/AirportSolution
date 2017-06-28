package servlets;

import entities.FlightHib;
import repository.FlightRepositoryHib;
import util.TimeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private FlightRepositoryHib repository = new FlightRepositoryHib();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String flightNumber = request.getParameter("flightNumber");
        Byte directionType = Byte.parseByte(request.getParameter("type"));
        String leavingFrom = request.getParameter("leavingFrom");
        String arrivalTo = request.getParameter("arrivalTo");
        String leavingTimeString = request.getParameter("leavingTime");
        String arrivalTimeString = request.getParameter("arrivalTime");

        Timestamp leavingTimeStamp = TimeUtils.toTimeStamp(leavingTimeString);
        Timestamp arrivalTimeStamp = TimeUtils.toTimeStamp(arrivalTimeString);
        FlightHib flight = new FlightHib(flightNumber, directionType, leavingFrom, arrivalTo, leavingTimeStamp, arrivalTimeStamp);
        System.out.println(flight);
        repository.addNewEntity(flight);
        response.sendRedirect("http://localhost:8080/info");
    }
}
