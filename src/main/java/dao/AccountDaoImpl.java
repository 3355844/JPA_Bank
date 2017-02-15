package dao;

import models.Account;
import service.PersistenceManager;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 33558 on 14.02.2017.
 */
public class AccountDaoImpl implements AccountDao {

    EntityManager entityManager = PersistenceManager.getEntityManager();

    @Override
    public Account getById(int id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public void add(Account account) {
        entityManager.getTransaction().begin();
        entityManager.merge(account);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Account account) {
        entityManager.getTransaction().begin();
        entityManager.remove(account);
        entityManager.getTransaction().commit();
    }

    @Override
    public Account update(Account account) {
        entityManager.getTransaction().begin();
        entityManager.merge(account);
        entityManager.getTransaction().commit();
        return getById(account.getId());
    }

    @Override
    public List<Account> accounts() {
        TypedQuery<Account> nameQuery = entityManager.createNamedQuery("Account.getAll", Account.class);
        return nameQuery.getResultList();
    }

    @Override
    public List<Account> getByCurrencyAndHumanId(String currency, int idHuman) {
        Query query = entityManager.createNamedQuery("Account.getByHumanIdAndCurrency", Account.class);
        query.setParameter("currency", currency);
        query.setParameter("id", idHuman);
        return query.getResultList();
    }
}
