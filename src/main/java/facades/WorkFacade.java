package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class WorkFacade {
    private static WorkFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private WorkFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static WorkFacade getWorkFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new WorkFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
