package servlet;

import model.User;
import store.HibStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibStore store = HibStore.getInstance();
        System.out.println("Проверка емейла" + req.getParameter("email"));
        store.addUser(new User(req.getParameter("email"),
                    req.getParameter("name"),
                    req.getParameter("password")));
        req.getRequestDispatcher("login.html").forward(req, resp);
    }
}
