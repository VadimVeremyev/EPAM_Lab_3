package by.bsuir.committee.servlets;

import by.bsuir.committee.entity.Enrollee;
import by.bsuir.committee.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AddServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(String.valueOf(AddServlet.class));

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Adding routine started...");

            Enrollee enrollee = new Enrollee();
            enrollee.setFirstName(req.getParameter("firstName"));
            enrollee.setMiddleName(req.getParameter("middleName"));
            enrollee.setLastName(req.getParameter("lastName"));
            enrollee.setFacultyName(req.getParameter("facultyName"));
            enrollee.setId(enrollee.hashCode());
            
            DaoFactory.getEnrolleeDao().addEnrollee(enrollee);
            logger.info("Add: Successful");
            resp.sendRedirect(req.getContextPath() + "/enrollees");
        } catch (Exception e) {
            logger.info(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req, resp);
        }
    }
}
