package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Category;
import model.Item;
import model.User;
import org.hibernate.HibernateException;
import store.HibStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class AddServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibStore store = HibStore.getInstance();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8));
        Item item = GSON.fromJson(req.getReader(), Item.class);
        System.out.println(item);
        item.setUser(user);
        item.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        item.setDone(false);
        System.out.println(item);
        try {
            store.addItem(item);
            System.out.println(item);
            writer.print("200");
        } catch (HibernateException e) {
            writer.print("409");
        }
        writer.flush();
    }
}
