package by.bsuir.committee.servlets;

import by.bsuir.committee.dao.DaoFactory;
import by.bsuir.committee.entity.Enrollee;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class DeleteServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(String.valueOf(DeleteServlet.class));

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            logger.info("Editing routine started...");
            DaoFactory.getEnrolleeDao().deleteEnrollee(Integer.parseInt(request.getParameter("id")));
            logger.info("Edit: Successful");
            response.sendRedirect(request.getContextPath() + "/enrollees");
        }
        catch(Exception e) {
            logger.info(e.getMessage());
            response.sendRedirect(request.getContextPath() + "/enrollees");
        }
    }
}
