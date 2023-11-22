package org.example.services;

import org.example.Screens.Stage;
import org.example.repositories.StageDaoImpl;

public class StageService {
    private static StageDaoImpl dao = StageDaoImpl.getInstance();
    public static Stage continueLastSave() {
        Stage stageTest = dao.getById(countRows());
        Stage newnewStage = new Stage(stageTest, 1);
        System.out.println("EAGERLY FETCHED FROM PROJECTILES EAGERLY FETCHED PLAYER: " + stageTest.getPlayer().projectileList);
        return newnewStage;
    }
    public static void insert(Stage stage) {
        dao.insert(stage);
    }
    public static void update(Stage stage) {
        dao.update(stage);
    }
    public static int countRows() {
        return dao.countRows().intValue();
    }

}
