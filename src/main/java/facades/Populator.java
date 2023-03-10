/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;

import javax.persistence.EntityManagerFactory;

import entities.Person;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade personFacade = PersonFacade.getPersonFacade(emf);
        personFacade.create(new PersonDTO(new Person("First 1", "Last 1","email1@.com","1","1")));
        personFacade.create(new PersonDTO(new Person("First 2", "Last 2","email2@.com","2","2")));
        personFacade.create(new PersonDTO(new Person("First 3", "Last 3","email3@.com","3","3")));

    }
    
    public static void main(String[] args) {
        populate();
    }
}
