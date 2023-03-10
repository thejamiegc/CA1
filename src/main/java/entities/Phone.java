package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Phone.deleteAllRows", query = "DELETE from Phone")
@Table(name = "Phone")
public class Phone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_id")
    private Person person;

    @Column(name = "number", length = 45)
    private String number;

    @Column(name = "countrycode", length = 45)
    private String countrycode;

    @Column(name = "description", length = 45)
    private String description;

    public Phone() {
    }

    public Phone(String number, String countrycode, String description) {
        this.number = number;
        this.countrycode = countrycode;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", person=" + person +
                ", number='" + number + '\'' +
                ", countrycode='" + countrycode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}