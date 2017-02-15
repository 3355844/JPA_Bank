package dao;

import models.Transaction;

import java.util.Date;
import java.util.List;

/**
 * Created by 33558 on 14.02.2017.
 */
public interface TransactionDao {
    void add(Transaction transaction);

    Transaction getById(int id);

    List<Transaction> getAll();

    List<Transaction> getByDate(Date date);
}
