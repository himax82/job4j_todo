package servlet;

import model.Item;
import model.User;
import store.HibStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AffairsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        HibStore store = HibStore.getInstance();
        List<Item> itemList = store.findAll(user.getId());
        ObjectMapper mapper = new ObjectMapper();
        String itemsAsString = mapper.writeValueAsString(itemList);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(itemsAsString);
    }

}
