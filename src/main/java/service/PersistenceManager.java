package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by 33558 on 15.02.2017.
 */
public abstract class PersistenceManager {
   static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bank");
   static EntityManager entityManager  = factory.createEntityManager();

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public PersistenceManager() {
    }

    public static void shutDown() {
        entityManager.close();
        factory.close();
    }
}
