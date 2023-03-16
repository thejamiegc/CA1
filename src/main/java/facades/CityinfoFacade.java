package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
