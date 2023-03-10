/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class PersonDTO {
    private long id;
    private String email;
    private String firstname;
    private String lastname;
    private String gender;
    private String relationshipStatus;


    public PersonDTO(String email, String firstname, String lastname) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static List<PersonDTO> getDtos(List<Person> rms){
        List<PersonDTO> personDTOS = new ArrayList();
        rms.forEach(rm->personDTOS.add(new PersonDTO(rm)));
        return personDTOS;
    }


    public PersonDTO(Person person) {
        if(person.getId() != null)
            this.id = person.getId();
            this.email = person.getEmail();
            this.firstname = person.getFirstname();
            this.lastname = person.getLastname();
            this.gender = person.getGender();
            this.relationshipStatus = person.getRelationshipstatus();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", relationshipStatus='" + relationshipStatus + '\'' +
                '}';
    }
}
