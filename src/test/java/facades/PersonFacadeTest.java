package facades;

import dtos.PersonDTO;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = PersonFacade.getPersonFacade(emf);
       EntityManager em = emf.createEntityManager();
       em.createNativeQuery("ALTER TABLE Person AUTO_INCREMENT=1");
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        facade.create(new PersonDTO(new Person("First 1", "Last 1","email1@.com","1","1")));
        facade.create(new PersonDTO(new Person("First 2", "Last 2","email2@.com","2","2")));

    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
            em.createNativeQuery("ALTER TABLE Person AUTO_INCREMENT=1");
        } finally {
            em.close();
        }
    }

    @Test
    public void testPersonCount() throws Exception {
        System.out.println("testing Person count");
        assertEquals(2, facade.getPersonCount(), "Expects two rows in the database");
    }


    @Test
    public void testGetAllPeople(){
        System.out.println("testing get all people");
        List<PersonDTO> people = facade.getAll();

        assertEquals("First 1",people.get(0).getFirstname(),"Expects the first person in the list");
        assertEquals("First 2",people.get(1).getFirstname(),"Expects the second person in the list");
    }


    @Test
    public void testGetPersonById(){
        assertEquals("First 1",facade.getById(1).getFirstname());
    }

//    @Test
//    public void testUpdatePersonById(){
//        System.out.println("Testing name change");
//        PersonDTO personDTO = new PersonDTO(new Person("First 2.5","Last 2","email2@.com","2","2"));
//        PersonDTO actualDTO = facade.updatePersonById(2,personDTO);
//        assertEquals("First 2.5",actualDTO.getFirstname());
//    }

    @Test
    public void testUpdatePersonById(){
        System.out.println("Testing name change PT 2");
        List<PersonDTO> people = facade.getAll();
        System.out.println(people.get(1).getId());
        PersonDTO personDTO = facade.updatePersonById(people.get(1).getId(),new PersonDTO("First 2.5","Last 2","email2@.com","2","2"));
        assertEquals("First 2.5",personDTO.getFirstname());

    }


}
