package org.example.services;

import org.example.Models.Projectile;
import org.example.repositories.ProjectileDao;
import org.example.repositories.ProjectileDaoImpl;

import java.util.List;

public class ProjectileService {
    private static ProjectileDaoImpl dao = new ProjectileDaoImpl();

    public static List<Projectile> getAll() {
        return dao.getAll();
    }
    public static Projectile getById(int id) {
        return dao.getById(id);
    }
    public static void insert(Projectile projectile) {
        dao.insert(projectile);
    }
    public static void delete(Projectile projectile) {
        dao.delete(projectile);
    }

}
