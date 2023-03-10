package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Work.deleteAllRows", query = "DELETE from Work")
@Table(name = "Work")
public class Work implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 45)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @ManyToMany(mappedBy = "works")
    private Set<Person> people = new LinkedHashSet<>();

    public Work() {
    }

    public Work(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", people=" + people +
                '}';
    }
}