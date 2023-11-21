package org.example.services;

import org.example.Screens.Stage;
import org.example.repositories.UniversalDao;

import java.util.Optional;

public class UniversalService {
    private static UniversalDao univDao = new UniversalDao();

    public static void saveState(Stage stage) {
        univDao.save(stage);
    }
    public static Stage getStage(int id) {
        return univDao.getStageById(id);
    }
}