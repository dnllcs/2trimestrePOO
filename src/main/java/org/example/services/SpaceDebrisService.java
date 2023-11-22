package org.example.services;

import org.example.Models.SpaceDebris;
import org.example.repositories.SpaceDebrisDao;
import org.example.repositories.SpaceDebrisDaoImpl;

import java.util.List;

public class SpaceDebrisService {
    private static SpaceDebrisDaoImpl dao = SpaceDebrisDaoImpl.getInstance();
    public static List<SpaceDebris> getAll() {
        return dao.getAll();
    }
    public static SpaceDebris getById(int id) {
        return dao.getById(id);
    }
    public static void insert(SpaceDebris spaceDebris) {
        dao.insert(spaceDebris);
    }
    public static void delete(SpaceDebris spaceDebris) {
        dao.delete(spaceDebris);
    }

}
