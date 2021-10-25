package servlet;

import store.HibStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        HibStore store = new HibStore();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        store.update(Integer.valueOf(id));
    }
}