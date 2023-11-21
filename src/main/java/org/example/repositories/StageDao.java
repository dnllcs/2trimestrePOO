package org.example.repositories;

import org.example.Screens.Stage;

import java.util.List;

public interface StageDao {
    List<Stage> getAll();
    Stage getById(int id);
    void insert(Stage stage);
    void update(Stage stage);
    void delete(Stage stage);
}
