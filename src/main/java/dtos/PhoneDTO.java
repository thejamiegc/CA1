package dtos;

import entities.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneDTO {
    private long id;
    private String number;
    private String countryCode;
    private String description;

    public PhoneDTO(String number, String countryCode, String description) {
        this.number = number;
        this.countryCode = countryCode;
        this.description = description;
    }

    public PhoneDTO(Phone phone){
        if(phone.getId() != null) {
            this.id = phone.getId();
            this.number = phone.getNumber();
            this.countryCode = phone.getCountrycode();
            this.description = phone.getDescription();
        }
    }

    public static List<PhoneDTO> getDtos(List<Phone> phones){
        List<PhoneDTO> phoneDTOS = new ArrayList();
        phones.forEach(phone->phoneDTOS.add(new PhoneDTO(phone)));
        return phoneDTOS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
