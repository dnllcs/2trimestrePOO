package org.example.repositories;

import org.example.Models.Projectile;
import java.util.List;

public interface ProjectileDao {

    List<Projectile> getAll();
    Projectile getById(int id);
    void insert(Projectile projectile);
    void update(Projectile projectile);
    void delete(Projectile projectile);
}
