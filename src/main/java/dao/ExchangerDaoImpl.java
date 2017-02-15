package dao;

import models.Exchanger;
import service.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by 33558 on 14.02.2017.
 */
public class ExchangerDaoImpl implements ExchangerDao {

    EntityManager entityManager = PersistenceManager.getEntityManager();

    @Override
    public void add(Exchanger exchanger) {
        entityManager.getTransaction().begin();
        entityManager.merge(exchanger);
        entityManager.getTransaction().commit();
    }

    @Override
    public Exchanger getById(int id) {
        return entityManager.find(Exchanger.class, id);
    }

    @Override
    public List<Exchanger> getByDate(Date date) {
        Query query = entityManager.createNamedQuery("Exchanger.getByDate", Exchanger.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Exchanger> getAll() {
        TypedQuery<Exchanger> namedQuery = entityManager.createNamedQuery("Exchanger.getAll", Exchanger.class);
        return namedQuery.getResultList();
    }
}
