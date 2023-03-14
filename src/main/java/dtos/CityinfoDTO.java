package dtos;

import entities.Cityinfo;

import java.util.ArrayList;
import java.util.List;

public class CityinfoDTO {
    private int zipcode;
    private String city;

    public CityinfoDTO(int zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }

    public CityinfoDTO(Cityinfo cityinfo){
        if(cityinfo.getId() != null){
            this.zipcode = cityinfo.getId();
            this.city = cityinfo.getCity();
        }
    }

    public static List<CityinfoDTO> getDtos(List<Cityinfo> cityinfoList){
        List<CityinfoDTO> cityinfoDTOS = new ArrayList();
        cityinfoList.forEach(cityinfo->cityinfoDTOS.add(new CityinfoDTO(cityinfo)));
        return cityinfoDTOS;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityinfoDTO{" +
                "zipcode=" + zipcode +
                ", city='" + city + '\'' +
                '}';
    }
}
