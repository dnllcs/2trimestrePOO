package org.example.services;

import org.example.Models.Enemy;
import org.example.Screens.Stage;
import org.example.repositories.UniversalDao;

public class StageService {


    public static Stage buildUpStage(int id) {
        Stage stageTest = UniversalService.getStage(id);
        Stage newnewStage = new Stage(stageTest, 5);
        System.out.println("EAGERLY FETCHED FROM PROJECTILES EAGERLY FETCHED PLAYER: " + stageTest.getPlayer().projectileList);
        stageTest.getPlayer().getScore();
        return newnewStage;
    }
    public static Long getRowCount() {
        return UniversalDao.getInstance().countRows();
    }
}
