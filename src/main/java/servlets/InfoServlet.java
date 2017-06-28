package servlets;

import entities.FlightHib;
import entities.FlightJDBC;
import repository.FlightRepositoryHib;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static util.EntityUtil.toJDBCEntityList;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {
    private FlightRepositoryHib repository = new FlightRepositoryHib();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("'/info'  ->  'DO GET' method:");


        if (request.getParameter("action") != null) delete(request);
        List<FlightJDBC> flightList = toJDBCEntityList(repository.getAll());

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("'/info'  ->  'DO POST' method:");

        if (request.getParameter("action").equals("skip")) response.sendRedirect("http://localhost:8080/info");
        if (request.getParameter("action").equals("filter-sort")) filterSort(request, response);
    }

    private void filterSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FlightJDBC> flightList = toJDBCEntityList(repository.getAll());

        String typeFilter = request.getParameter("typeFilter"); // FILTER
        String sortFilter = request.getParameter("sortFilter"); // SORT

//filter>
        if (typeFilter != null && !typeFilter.equals("all")) {
            boolean typeFilterBoolean = Boolean.parseBoolean(typeFilter);
            flightList = flightList.stream()
                    .filter(e -> e.getDirectionType() == typeFilterBoolean)
                    .sorted(typeFilterBoolean ? sortByArrival : sortByLeaving)
                    .collect(Collectors.toList());
        }
//sort>
        if (sortFilter != null && sortFilter.equals("LeavingTime"))
            flightList.sort(sortByLeaving);
        else if (sortFilter != null && sortFilter.equals("ArrivalTime"))
            flightList.sort(sortByArrival);

        request.setAttribute("list", flightList);
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request) {
        if (!request.getParameter("action").equals("delete")) return;
        int id = Integer.parseInt(request.getParameter("id"));
        repository.removeById(id);
    }

    private Comparator<FlightJDBC> sortByArrival = (entity1, entity2) -> {
        if (entity1.getArrivalTime().isBefore(entity2.getArrivalTime())) return -1;
        if (entity1.getArrivalTime().isAfter(entity2.getArrivalTime())) return 1;
        return 0;
    };
    private Comparator<FlightJDBC> sortByLeaving = (entity1, entity2) -> {
        if (entity1.getLeavingTime().isBefore(entity2.getLeavingTime())) return -1;
        if (entity1.getLeavingTime().isAfter(entity2.getLeavingTime())) return 1;
        return 0;
    };
}