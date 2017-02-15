package dao;

import models.Exchanger;
import models.Transaction;
import service.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by 33558 on 14.02.2017.
 */
public class TransactionDaoImpl implements TransactionDao {
    EntityManager entityManager = PersistenceManager.getEntityManager();

    @Override
    public void add(Transaction transaction) {
        entityManager.getTransaction().begin();
        entityManager.merge(transaction);
        entityManager.getTransaction().commit();
    }

    @Override
    public Transaction getById(int id) {
        return entityManager.find(Transaction.class, id);
    }

    @Override
    public List<Transaction> getAll() {
        TypedQuery<Transaction> nameQuery = entityManager.createNamedQuery("Transaction.getAll", Transaction.class);
        return nameQuery.getResultList();
    }

    @Override
    public List<Transaction> getByDate(Date date) {
        Query query = entityManager.createNamedQuery("Transaction.getByDate", Exchanger.class);
        query.setParameter("date", date);
        return query.getResultList();
    }
}
