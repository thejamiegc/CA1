package facades;

import dtos.PersonDTO;

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
    public PersonDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person rm = em.find(Person.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new PersonDTO(rm);
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
        List<Person> rms = query.getResultList();
        return PersonDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = getPersonFacade(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
        Person tmpPerson = fe.getById2(4);

        System.out.println(tmpPerson.getHobbies());
    }

    public PersonDTO updatePersonById(long id, PersonDTO personDTO) {
        EntityManager em = emf.createEntityManager();
        Person tmpPerson = em.find(Person.class, id);
        if(tmpPerson != null){
            tmpPerson = new Person(personDTO.getFirstname(),personDTO.getLastname(),personDTO.getEmail(),personDTO.getGender(),personDTO.getRelationshipStatus());
            tmpPerson.setId(id);
            try{
                em.getTransaction().begin();
                em.merge(tmpPerson);
                em.getTransaction().commit();
            }finally {
                em.close();
            }
        }
        return new PersonDTO(tmpPerson);

    }

    public PersonDTO getPersonByHobby(Person person) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT r FROM Hobby r JOIN Person p WHERE r.id = p.id", Hobby.class);

        // SELECT Person_id FROM Person_has_hobby WHERE Hobby_id = ?
        // SELECT firstname FROM Person WHERE Person_id = ^^
        // SELECT name FROM Hobby WHERE Hobby_id = ?
        System.out.println(person.getHobbies());
        return null;
    }


    public Person getById2(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person rm = em.find(Person.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return rm;
    }
}
