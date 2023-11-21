package org.example.services;

import org.example.Models.Enemy;
import org.example.repositories.EnemyDao;
import org.example.repositories.EnemyDaoImpl;

import java.util.List;

public class EnemyService {
    private static EnemyDao dao = new EnemyDaoImpl();

    public static List<Enemy> getAll() {
        return dao.getAll();
    }
    public static Enemy getById(int id) {
        return dao.getById(id);
    }
    public static void insert(Enemy enemy) {
        dao.insert(enemy);
    }
    public static void delete(Enemy enemy) {
        dao.delete(enemy);
    }

}
