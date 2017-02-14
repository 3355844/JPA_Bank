package dao;

import models.Exchanger;

import java.util.Date;
import java.util.List;

/**
 * Created by 33558 on 14.02.2017.
 */
public interface ExchangerDao {
    void add(Exchanger exchanger);
    Exchanger getById(int id);
    List<Exchanger> getByDate (Date date);
    List<Exchanger> getAll();
}
