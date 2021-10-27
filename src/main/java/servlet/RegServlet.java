package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import store.HibStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class RegServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello");
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8));
        User user = GSON.fromJson(req.getReader(), User.class);
        HibStore store = HibStore.getInstance();
        if (store.findByEmail(user.getEmail()) == null) {
            store.addUser(user);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            writer.print("200");
        } else {
            writer.println("400");
        }
        writer.flush();
    }
}
