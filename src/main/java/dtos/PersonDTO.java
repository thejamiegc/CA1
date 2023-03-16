/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Hobby;
import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class PersonDTO {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private String relationshipstatus;


    public PersonDTO(String firstname, String lastname,String email, String gender, String relationshipstatus) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.relationshipstatus = relationshipstatus;
    }

    public static List<PersonDTO> getDtos(List<Person> people){
        List<PersonDTO> personDTOS = new ArrayList();
        people.forEach(person->personDTOS.add(new PersonDTO(person)));
        return personDTOS;
    }


    public PersonDTO(Person person) {
        if(person.getId() != null) {
            this.id = person.getId();
            this.email = person.getEmail();
            this.firstname = person.getFirstname();
            this.lastname = person.getLastname();
            this.gender = person.getGender();
            this.relationshipstatus = person.getRelationshipstatus();
        }
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
        return relationshipstatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipstatus = relationshipstatus;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", relationshipStatus='" + relationshipstatus + '\'' +
                '}';
    }
}
