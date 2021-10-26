package store;

import model.Item;
import model.User;

import java.util.List;

public interface Store {

    Item addItem(Item item);

    User addUser(User user);

    void update(int id);

    List<Item> findAll(int id);

    User findByEmail(String email);
}
