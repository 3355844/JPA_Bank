package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by 33558 on 15.02.2017.
 */
public abstract class PersistenceManager {
    EntityManagerFactory factory ;
    EntityManager entityManager ;

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public PersistenceManager() {
        this.factory = Persistence.createEntityManagerFactory("Bank");
        this.entityManager = factory.createEntityManager();
    }

    public void shutDown() {
        entityManager.close();
        factory.close();
    }
}
