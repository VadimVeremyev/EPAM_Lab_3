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

public class RegisterServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(String.valueOf(RegisterServlet.class));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Register routine started...");
            User user = DaoFactory.getUserDao().getUserByName(req.getParameter("name"));

            if (user != null) {
                req.setAttribute("message", "This name already exists");
                logger.info("Register: Name already exists");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            } else {
                DaoFactory.getUserDao().addUser(req.getParameter("name"), req.getParameter("password"));
                logger.info("Register: Successful");
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            req.setAttribute("message", "Error");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}
