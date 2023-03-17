package facades;

import dtos.AddressDTO;
import dtos.HobbyDTO;
import entities.Address;
import entities.Hobby;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AddressFacade {
    private static AddressFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private AddressFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static AddressFacade getAddressFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    public AddressDTO createAddress(AddressDTO addressDTO){
        Address address = new Address(addressDTO.getStreetname(),addressDTO.getStreetnumber(),addressDTO.getHometype());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AddressDTO(address);
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
