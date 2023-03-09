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
    }


    
    
    
    
}
