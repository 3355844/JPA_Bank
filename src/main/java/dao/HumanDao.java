package dao;

import models.Human;

import java.util.List;

/**
 * Created by 33558 on 14.02.2017.
 */
public interface HumanDao {
    List<Human> getAll();

    Human getById(int id);

    void add(Human human);

    void delete(Human human);

    Human update(Human human);
}
