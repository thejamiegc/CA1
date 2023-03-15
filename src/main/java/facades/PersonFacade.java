package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import entities.Hobby;
import entities.Person;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PersonDTO create(PersonDTO personDTO){
        Person person = new Person(personDTO.getFirstname(), personDTO.getLastname(), personDTO.getEmail(),personDTO.getGender(),personDTO.getRelationshipStatus());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    public long getPersonCount(){
        EntityManager em = getEntityManager();
        try{
            long personCount = (long)em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
            return personCount;
        }finally{  
            em.close();
        }
    }
    
    public List<PersonDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT r FROM Person r", Person.class);
        List<Person> personList = query.getResultList();
        return PersonDTO.getDtos(personList);
    }

    public List<PersonDTO> getPersonByName(String firstname) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.firstname =:first",Person.class);
        query.setParameter("first",firstname);
        return PersonDTO.getDtos(query.getResultList());
    }


    public Person getPersonById(Long id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.id =:id",Person.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    public PersonDTO updatePersonById(long id, PersonDTO expectedPersonDTO) {
        EntityManager em = emf.createEntityManager();
        Person person = new Person(expectedPersonDTO.getFirstname(),expectedPersonDTO.getLastname(),expectedPersonDTO.getEmail(),expectedPersonDTO.getGender(),expectedPersonDTO.getRelationshipStatus());
        person.setId(id);
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
        return new PersonDTO(person);
    }

    public void deletePerson(Person person1) {
        EntityManager em = emf.createEntityManager();
        if (!em.contains(person1)) {
            em.getTransaction().begin();
            person1 = em.merge(person1);
            em.remove(person1);
            em.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        PersonFacade personFacade = new PersonFacade();
        Person person = new Person("Hej","Test","mail","1","nej");
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        List<PersonDTO> personList = personFacade.getAll();
        System.out.println(personList);
        personFacade.deletePerson(person);
        personList = personFacade.getAll();
        System.out.println(personList);
    }

    public List<PersonDTO> getPeopleByHobby(long id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.id = :hobbyId", Person.class);
        query.setParameter("hobbyId",id);
        List<Person> people = query.getResultList();
        return PersonDTO.getDtos(people);
    }

    public void deletePersonByID(long id) {
        EntityManager em = emf.createEntityManager();
        Person person1 = new Person();
        person1.setId(id);
        if (!em.contains(person1)) {
            em.getTransaction().begin();
            person1 = em.merge(person1);
            em.remove(person1);
            em.getTransaction().commit();
        }
    }
}
