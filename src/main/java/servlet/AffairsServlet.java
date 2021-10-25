package servlet;

import model.Item;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Stats !!!");
        List<Item> itemList = new HibStore().findAll();
        System.out.println("List geting");
        ObjectMapper mapper = new ObjectMapper();
        String itemsAsString = mapper.writeValueAsString(itemList);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(itemsAsString);
    }
}
