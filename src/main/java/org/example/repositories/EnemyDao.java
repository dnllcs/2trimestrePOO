package org.example.repositories;

import org.example.Models.Enemy;
import java.util.List;

public interface EnemyDao {

    List<Enemy> getAll();
    Enemy getById(int id);
    void insert(Enemy enemy);
    void update(Enemy enemy);
    void delete(Enemy enemy);
}
