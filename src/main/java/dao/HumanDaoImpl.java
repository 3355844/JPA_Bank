package dao;

import models.Human;
import service.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by 33558 on 14.02.2017.
 */
public class HumanDaoImpl implements HumanDao {
    EntityManager entityManager = PersistenceManager.getEntityManager();

    @Override
    public List<Human> getAll() {
        TypedQuery<Human> nameQuery = entityManager.createNamedQuery("Human.getAll", Human.class);
        return nameQuery.getResultList();
    }

    @Override
    public Human getById(int id) {
        return entityManager.find(Human.class, id);
    }

    @Override
    public Human add(Human human) {
        entityManager.getTransaction().begin();
        Human humanDB =  entityManager.merge(human);
        entityManager.getTransaction().commit();
        return humanDB;
    }

    @Override
    public void delete(Human human) {
        entityManager.getTransaction().begin();
        entityManager.remove(human);
        entityManager.getTransaction().commit();
    }

    @Override
    public Human update(Human human) {
        entityManager.getTransaction().begin();
        entityManager.merge(human);
        entityManager.getTransaction().commit();
        return getById(human.getIdHuman());
    }
}
