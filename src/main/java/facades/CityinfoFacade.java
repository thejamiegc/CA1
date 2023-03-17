package facades;

import dtos.CityinfoDTO;
import dtos.HobbyDTO;
import entities.Cityinfo;
import entities.Hobby;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class CityinfoFacade {
    private static CityinfoFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CityinfoFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CityinfoFacade getCityinfoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityinfoFacade();
        }
        return instance;
    }

    public List<CityinfoDTO> getAllCities() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cityinfo> query = em.createQuery("SELECT c FROM Cityinfo c", Cityinfo.class);
        List<Cityinfo> cityinfoList = query.getResultList();
        return CityinfoDTO.getDTOS(cityinfoList);
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



}
