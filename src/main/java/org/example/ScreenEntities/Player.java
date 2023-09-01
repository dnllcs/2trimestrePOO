package org.example.ScreenEntities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player extends GraphicalElement {

	public List<GraphicalElement> projectileList = new ArrayList<>();
	private int score;
	private int hitpoints = 3;
	public static int MOVEMENT = 5;
	private static final int STARTING_POSITION_X = 100;
	private static final int STARTING_POSITION_Y = 100;




	public Player() {
		positionX = STARTING_POSITION_X;
		positionY = STARTING_POSITION_Y;
	}

	public void load() {
		super.loadImage("player2");
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
	}
	public void reload() {
		positionX += movementX;
		positionY += movementY;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHitpoints() {
		return this.hitpoints;
	}
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	//logica de movimentacao do personagem
	public void move(KeyEvent e) {
	    int key = e.getKeyCode();
	    switch (key) {
	        case KeyEvent.VK_UP:
	            movementY = -MOVEMENT;
	            break;
	        case KeyEvent.VK_DOWN:
	            movementY = MOVEMENT;
	            break;
	        case KeyEvent.VK_LEFT:
	            movementX = -MOVEMENT;
	            break;
	        case KeyEvent.VK_RIGHT:
	            movementX = MOVEMENT;
	            break;
	        default:
	            break;
	    }
	}
	//logica para parar a movimentacao do personagem
	public void stop(KeyEvent e) {
	    int key = e.getKeyCode();
	    switch (key) {
	        case KeyEvent.VK_UP:
	            movementY = 0;
	            break;
	        case KeyEvent.VK_DOWN:
	            movementY = 0;
	            break;
	        case KeyEvent.VK_LEFT:
	            movementX = 0;
	            break;
	        case KeyEvent.VK_RIGHT:
	            movementX = 0;
	            break;
	        default:
	            break;
	    }
	}
	//barra de espaco instancia um projeito movendo na horizontal
	//tecla R instancia tres projeits, dois movendo na diagonal e um na horizontal
	public void fireProjectile(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			projectileList.add(new DefaultProjectile(getPositionX(), getPositionY()));
		}
		else if(key == KeyEvent.VK_R) {
			projectileList.add(new SpecialProjectile(getPositionX(), getPositionY(), 12, 4));
			projectileList.add(new SpecialProjectile(getPositionX(), getPositionY(), 12, 0));
			projectileList.add(new SpecialProjectile(getPositionX(), getPositionY(), 12, -4));
		}
	}
}