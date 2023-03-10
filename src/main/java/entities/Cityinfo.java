package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Cityinfo.deleteAllRows", query = "DELETE from Cityinfo")
@Table(name = "Cityinfo")
public class Cityinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zipcode", nullable = false)
    private int zipcode;

    @Column(name = "city", length = 45)
    private String city;

    public Cityinfo() {

    }

    public Cityinfo(int zipcode, String city, Set<Address> addresses) {
        this.zipcode = zipcode;
        this.city = city;
        this.addresses = addresses;
    }

    @OneToMany(mappedBy = "cityinfoZipcode")
    private Set<Address> addresses = new LinkedHashSet<>();

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return zipcode;
    }

    public void setId(Integer id) {
        this.zipcode = id;
    }

    @Override
    public String toString() {
        return "Cityinfo{" +
                "zipcode=" + zipcode +
                ", city='" + city + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}