package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import entities.Hobby;
import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class HobbyFacade {
    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HobbyFacade getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public HobbyDTO create(HobbyDTO hobbyDTO){
        Hobby hobby = new Hobby(hobbyDTO.getName(),hobbyDTO.getWikiLink(),hobbyDTO.getCategory(),hobbyDTO.getType());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDTO(hobby);
    }


    public List<HobbyDTO> getAllHobbies() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
        List<Hobby> hobbies = query.getResultList();
        return HobbyDTO.getDTOS(hobbies);
    }

    public void deleteHobbyById(long hobbyId) {
        EntityManager em = emf.createEntityManager();
        Hobby hobby1 = new Hobby();
        hobby1.setId(hobbyId);
        if (!em.contains(hobby1)) {
            em.getTransaction().begin();
            hobby1 = em.merge(hobby1);
            em.remove(hobby1);
            em.getTransaction().commit();
        }
    }

    public HobbyDTO updateHobbyById(long id, HobbyDTO hobbyDTO) {
        EntityManager em = emf.createEntityManager();
        Hobby hobby = new Hobby(hobbyDTO.getName(),hobbyDTO.getWikiLink(),hobbyDTO.getCategory(),hobbyDTO.getType());
        hobby.setId(id);
        em.getTransaction().begin();
        em.merge(hobby);
        em.getTransaction().commit();
        return new HobbyDTO(hobby);
    }
}
