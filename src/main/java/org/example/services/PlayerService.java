package org.example.services;

import org.example.Models.Player;
import org.example.repositories.PlayerDao;
import org.example.repositories.PlayerDaoImpl;

import java.util.List;

public class PlayerService {
    private static PlayerDaoImpl dao = PlayerDaoImpl.getInstance();

    public static List<Player> getAll() {
        return dao.getAll();
    }
    public static Player getById(int id) {
        return dao.getById(id);
    }
    public static void insert(Player player) {
        dao.insert(player);
    }
    public static void delete(Player player) {
        dao.delete(player);
    }

}
