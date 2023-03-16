//package facades;
//
//import dtos.HobbyDTO;
//import dtos.PersonDTO;
//import entities.Hobby;
//import entities.Person;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//
////Uncomment the line below, to temporarily disable this test
////@Disabled
//public class PersonFacadeTest {
//
//    private static EntityManagerFactory emf;
//    private static PersonFacade facade;
//    private static Person person1;
//    private static Person person2;
//    private static PersonDTO personDTO1;
//    private static PersonDTO personDTO2;
//    private static Hobby hobby1;
//    private static HobbyDTO hobbyDTO1;
//
//
//    public PersonFacadeTest() {
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//       emf = EMF_Creator.createEntityManagerFactoryForTest();
//       facade = PersonFacade.getPersonFacade(emf);
//       EntityManager em = emf.createEntityManager();
//       //em.createNativeQuery("ALTER TABLE Person AUTO_INCREMENT=1");
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
////        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
//    }
//
//    // Setup the DataBase in a known state BEFORE EACH TEST
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//
//        em.getTransaction().begin();
//        hobby1 = new Hobby("bullet journalling","link","sjov","pas");
//        em.persist(hobby1);
//        em.getTransaction().commit();
//        Set<Hobby> hobbySet = new LinkedHashSet<>();
//        hobbySet.add(hobby1);
//        em.getTransaction().begin();
//
//        person1 = new Person("Hans","Oge","mail@mail.dk","male","forever single",hobbySet);
//        person2 = new Person("Molly","Fisk","mail2@mail.dk","female","forever not single",hobbySet);
//        em.persist(person1);
//        em.persist(person2);
//        em.getTransaction().commit();
//
//
//        hobbyDTO1 = new HobbyDTO(hobby1);
//        personDTO1 = new PersonDTO(person1);
//        personDTO2 = new PersonDTO(person2);
//
//    }
//
//    @AfterEach
//    public void tearDown() {
////        Remove any data after each test was run
//        EntityManager em = emf.createEntityManager();
//        try {
//
//            em.getTransaction().begin();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            em.getTransaction().commit();
//            //em.createNativeQuery("ALTER TABLE Person AUTO_INCREMENT=1");
//        } finally {
//            em.close();
//        }
//    }
//
//    @Test
//    public void getPersonByFirstnameTest(){
//        List<PersonDTO> actualPersonList = facade.getPersonByName(personDTO1.getFirstname());
//        System.out.println(actualPersonList);
//        assertEquals(personDTO1.getFirstname(),actualPersonList.get(0).getFirstname());
//    }
//
//    @Test
//    public void getPersonByIdTest(){
//        Person actualPerson = facade.getPersonById(personDTO1.getId());
//        System.out.println(actualPerson);
//        assertEquals(personDTO1.getId(),actualPerson.getId());
//    }
//
//    @Test
//    public void updatePersonByIdTest(){
//        PersonDTO expectedPersonDTO = new PersonDTO("Lillia","Blubla","mail","dummygender","dummyrelation");
//        PersonDTO actualPersonDTO = facade.updatePersonById(personDTO1.getId(),expectedPersonDTO);
//        assertEquals(expectedPersonDTO.getFirstname(),actualPersonDTO.getFirstname());
//    }
//
////    @Test
////    public void deletePersonByIdTest(){
////        facade.deletePerson(person1);
////        assertNull(person1);
////    }
//
//    @Test
//    public void createPersonTest(){
//        PersonDTO expectedPersonDTO = new PersonDTO("Lillia","Blubla","mail","dummygender","dummyrelation");
//        PersonDTO actualPersonDTO = facade.create(expectedPersonDTO);
//        assertEquals(expectedPersonDTO.getFirstname(),actualPersonDTO.getFirstname());
//    }
//
//    @Test
//    public void getPeopleByHobbyTest(){
//        System.out.println(person1.getHobbies());
//        List<PersonDTO> personDTOList = facade.getPeopleByHobby(hobbyDTO1.getId());
//        System.out.println(personDTOList);
//        assertEquals(2,personDTOList.size());
//    }
//}
