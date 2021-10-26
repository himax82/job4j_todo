package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty
    private String description;

    private Timestamp created;

    @JsonProperty
    private Boolean done;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Item(String description, Timestamp created, Boolean done, User user) {
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
    }

    public Item() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", description='" + description + '\''
                + ", created=" + created + ", done=" + done + '}';
    }
}