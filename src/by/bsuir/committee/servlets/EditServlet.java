package by.bsuir.committee.servlets;

import by.bsuir.committee.entity.Enrollee;
import by.bsuir.committee.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class EditServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(String.valueOf(EditServlet.class));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Enrollee enrollee = DaoFactory.getEnrolleeDao().getEnrollee(id);

            if (enrollee != null) {
                req.setAttribute("client", enrollee);
                req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/enrollees");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/enrollees");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Editing routine started...");
            
            Enrollee enrollee = new Enrollee();
            enrollee.setFirstName(req.getParameter("firstName"));
            enrollee.setMiddleName(req.getParameter("middleName"));
            enrollee.setLastName(req.getParameter("lastName"));
            enrollee.setFacultyName(req.getParameter("facultyName"));
            enrollee.setId(enrollee.hashCode());
            
            DaoFactory.getEnrolleeDao().addEnrollee(enrollee);
            logger.info("Edit: Successful");
            resp.sendRedirect(req.getContextPath() + "/clients");
        } catch (Exception e) {
            logger.info(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
        }
    }
}
