package dao;

import models.Exchanger;

/**
 * Created by 33558 on 14.02.2017.
 */
public interface ExchangerDao {
    void add(Exchanger exchanger);
    void getById(int id);
}
