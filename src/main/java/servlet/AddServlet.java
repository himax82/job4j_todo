package servlet;

import model.Item;
import org.hibernate.HibernateException;
import store.HibStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibStore store = new HibStore();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        Item item = new Item(description, new Timestamp(System.currentTimeMillis()), false);
        try {
            store.add(item);
        } catch (HibernateException e) {
            System.out.println("Задача не добавлена");
        }
    }
}
