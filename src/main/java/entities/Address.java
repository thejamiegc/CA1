package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Address")
@NamedQuery(name="Address.deleteAllRows",query="DELETE from Address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cityinfo_zipcode")
    private Cityinfo cityinfoZipcode;

    @Column(name = "streetname", length = 100)
    private String streetname;

    @Column(name = "streetnumber", length = 45)
    private String streetnumber;

    @Column(name = "hometype", length = 45)
    private String hometype;

    public Address() {
    }

    public Address(String streetname, String streetnumber, String hometype) {
        this.streetname = streetname;
        this.streetnumber = streetnumber;
        this.hometype = hometype;
    }

    public String getHometype() {
        return hometype;
    }

    public void setHometype(String hometype) {
        this.hometype = hometype;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public Cityinfo getCityinfoZipcode() {
        return cityinfoZipcode;
    }

    public void setCityinfoZipcode(Cityinfo cityinfoZipcode) {
        this.cityinfoZipcode = cityinfoZipcode;
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

}