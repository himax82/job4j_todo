package store;

import model.Category;
import model.Item;
import model.User;

import java.util.Collection;
import java.util.List;

public interface Store {

    Item addItem(Item item);

    User addUser(User user);

    void update(int id);

    List<Item> findAll(int id);

    Collection<Category> findAllCategories();

    User findByEmail(String email);
}
