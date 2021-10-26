package servlet;

import model.Item;
import model.User;
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
        HibStore store = HibStore.HibStoreHolder.HOLDER_INSTANCE;
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        String description = req.getParameter("description");
        Item item = new Item(description, new Timestamp(System.currentTimeMillis()), false, user);
        try {
            store.addItem(item);
        } catch (HibernateException e) {
            System.out.println("Задача не добавлена");
        }
    }
}
