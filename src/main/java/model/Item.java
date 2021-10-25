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
    private Integer id;

    @JsonProperty
    private String description;

    private Timestamp created;

    @JsonProperty
    private Boolean done;

    public Item(String description, Timestamp created, Boolean done) {
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Item() {
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
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(description, item.description)
                && Objects.equals(created, item.created) && Objects.equals(done, item.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, done);
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", description='" + description + '\''
                + ", created=" + created + ", done=" + done + '}';
    }
}