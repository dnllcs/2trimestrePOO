package org.example.repositories;

import org.example.Models.Player;
import java.util.List;

public interface PlayerDao {

    List<Player> getAll();
    Player getById(int id);
    void insert(Player player);
    void update(Player player);
    void delete(Player player);
}
