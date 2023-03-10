package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Hobby.deleteAllRows", query = "DELETE from Hobby")
@Table(name = "Hobby")
public class Hobby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "wikilink", length = 500)
    private String wikilink;

    @Column(name = "category", length = 45)
    private String category;

    @Column(name = "type", length = 45)
    private String type;

    @ManyToMany(mappedBy = "hobbies")
    private Set<Person> people = new LinkedHashSet<>();

    public Hobby() {
    }

    public Hobby(String name, String wikilink, String category, String type) {
        this.name = name;
        this.wikilink = wikilink;
        this.category = category;
        this.type = type;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWikilink() {
        return wikilink;
    }

    public void setWikilink(String wikilink) {
        this.wikilink = wikilink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wikilink='" + wikilink + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", people=" + people +
                '}';
    }
}