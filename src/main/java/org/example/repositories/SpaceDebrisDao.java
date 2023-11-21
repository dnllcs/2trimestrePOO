package org.example.repositories;

import org.example.Models.SpaceDebris;
import java.util.List;

public interface SpaceDebrisDao {

    List<SpaceDebris> getAll();
    SpaceDebris getById(int id);
    void insert(SpaceDebris spaceDebris);
    void update(SpaceDebris spaceDebris);
    void delete(SpaceDebris spaceDebris);
}
