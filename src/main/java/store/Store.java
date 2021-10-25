package store;

import model.Item;

import java.util.List;

public interface Store {

    Item add(Item item);

    void update(Integer id);

    List<Item> findAll();
}
