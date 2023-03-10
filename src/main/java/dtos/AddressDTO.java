package dtos;

import entities.Address;
import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class AddressDTO {
    private long id;
    private String streetname;
    private String streetnumber;
    private String hometype;

    public AddressDTO(String streetname, String streetnumber, String hometype) {
        this.streetname = streetname;
        this.streetnumber = streetnumber;
        this.hometype = hometype;
    }

    public AddressDTO(Address address){
        if(address.getId() != null){
            this.id = address.getId();
            this.streetname = address.getStreetname();
            this.streetnumber = address.getStreetnumber();
            this.hometype = address.getHometype();
        }
    }

    public static List<AddressDTO> getDtos(List<Address> addressList){
        List<AddressDTO> addressDTOS = new ArrayList();
        addressList.forEach(address->addressDTOS.add(new AddressDTO(address)));
        return addressDTOS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getHometype() {
        return hometype;
    }

    public void setHometype(String hometype) {
        this.hometype = hometype;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", streetname='" + streetname + '\'' +
                ", streetnumber='" + streetnumber + '\'' +
                ", hometype='" + hometype + '\'' +
                '}';
    }
}
