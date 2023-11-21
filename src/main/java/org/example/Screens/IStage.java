package org.example.Screens;

import java.awt.*;

public interface IStage {
    void paint(Graphics g);
    void collision();
    void drawScore(Graphics2D g);
    void moveEntities();
    void inializeGraphicElements();
    void cleanUpMovingEntities();


}
