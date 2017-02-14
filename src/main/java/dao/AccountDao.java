package dao;

import models.Account;

import java.util.List;

/**
 * Created by 33558 on 14.02.2017.
 */
public interface AccountDao {
    Account getById(int id);

    void add(Account account);

    void delete(Account account);

    Account update(Account account);

    List<Account> accounts();

    List<Account> getByCurrencyAndHumanId(String currency, int idHuman);

}
