package store;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import model.Item;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Function;

public class HibStore implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private HibStore() {
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }


    public static class HibStoreHolder {
        public static final HibStore HOLDER_INSTANCE = new HibStore();
    }

    public static HibStore getInstance() {
        return HibStoreHolder.HOLDER_INSTANCE;
    }

    @Override
    public Item addItem(Item item) {
        return tx(session -> {
            session.save(item);
            return item;
        });
    }

    @Override
    public User addUser(User user) {
        return tx(session -> {
            session.save(user);
            return user;
                });
    }

    @Override
    public void update(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update model.Item set done=:done where id=:id");
        query.setParameter("id", id);
        query.setParameter("done", true);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Item> findAll(int id) {
        return tx(session -> {
            final Query query = session.createQuery("from model.Item where user.id=:id");
                    query.setParameter("id", id);
                    return query.list();
        });
    }

    @Override
    public User findByEmail(String email) {
        return (User) this.tx(session -> {
                final Query query = session.createQuery("from model.User where email=:email");
                query.setParameter("email", email);
                return query.uniqueResult();
        });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}

