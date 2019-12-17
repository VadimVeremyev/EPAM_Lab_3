package by.bsuir.committee.servlets;

import by.bsuir.committee.entity.Enrollee;
import by.bsuir.committee.entity.User;
import by.bsuir.committee.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(String.valueOf(AddServlet.class));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            logger.info("Log in routine started...");
            User user = DaoFactory.getUserDao().getUser(req.getParameter("name"), req.getParameter("password"));

            if(user != null) {
                logger.info("Log in: Successful");
                resp.sendRedirect(req.getContextPath()+"/clients");
            }
            else {
                logger.info("Log in: Wrong data input");
                resp.sendRedirect(req.getContextPath()+"/login");
            }
        }
        catch(Exception e) {
            logger.info(e.getMessage());
            resp.sendRedirect(req.getContextPath()+"/login");
        }
    }
}
