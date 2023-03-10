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
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(new Person("Moretext","EvenMoreText","asd@das.dk","gender","loner"));
            em.persist(new Person("bbb","ccc","aaa@aaa.com","binary","01"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testPersonCount() throws Exception {
        System.out.println("testing Person count");
        assertEquals(2, facade.getPersonCount(), "Expects two rows in the database");
    }

/*    @Test
    public void testGetAllPeople(){
        System.out.println("testing get all people");
        List<PersonDTO> people = facade.getAll();

        assertEquals("Moretext",people.get(0).getFirstname(),"Expects the first person in the list");
        assertEquals("bbb",people.get(1).getFirstname(),"Expects the second person in the list");
    }*/

//    @Test
//    public void testUpdatePerson{
//
//    }
    

}
