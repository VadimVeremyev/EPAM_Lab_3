package by.bsuir.committee.servlets;

import by.bsuir.committee.entity.Enrollee;
import by.bsuir.committee.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EnrolleeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Enrollee> enrollees = DaoFactory.getEnrolleeDao().getEnrollees();
        req.setAttribute("enrollees", enrollees);
        req.getRequestDispatcher("/WEB-INF/views/enrollee.jsp").forward(req, resp);
    }
}
