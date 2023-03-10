package facades;

import dtos.HobbyDTO;
import entities.Hobby;
import entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HobbyFacadeTest {

    private static EntityManagerFactory emf;
    private static HobbyFacade facade;
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = HobbyFacade.getHobbyFacade(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.persist(new Hobby("Test","test","category","type"));


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetAllHobbies(){
        List<HobbyDTO> hobbyDTOList = facade.getAllHobbies();
        assertEquals(1,hobbyDTOList.size(),"expecting the amount of hobbies");
    }


}