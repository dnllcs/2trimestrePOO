package org.example.services;

import org.example.Screens.Stage;
import org.example.repositories.StageDaoImpl;

public class StageService {
    private static StageDaoImpl dao = new StageDaoImpl();

    public static Stage continueLastSave() {
        Stage stageTest = dao.getById(countRows());
        Stage newnewStage = new Stage(stageTest, 5);
        System.out.println("EAGERLY FETCHED FROM PROJECTILES EAGERLY FETCHED PLAYER: " + stageTest.getPlayer().projectileList);
        return newnewStage;
    }
    public static void insert(Stage stage) {
        dao.insert(stage);
    }
    public static int countRows() {
        return dao.countRows().intValue();
    }

}
