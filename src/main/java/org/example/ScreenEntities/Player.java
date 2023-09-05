package org.example.ScreenEntities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.ImageIcon;

public class Player extends GraphicalElement {

	public List<GraphicalElement> projectileList = new ArrayList<>();
	public List<GraphicalElement> temp = new ArrayList<>();
	public Stack<SpecialProjectile> specialProjectileBuffer = new Stack<>();
	public int bufferIndex = 0;
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
		this.loadBuffer();
		super.loadImage("player2");
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
	}
	public void reload() {
		positionX += movementX;
		positionY += movementY;
		projectileList.addAll(temp);
		temp.clear();
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
	public void loadBuffer() {
		for(int i = 0;i<500;i++) {
			specialProjectileBuffer.push(new SpecialProjectile(-100, -100, 0, 0));
		}
	}

	public void fireProjectile(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			projectileList.add(new DefaultProjectile(getPositionX(), getPositionY()));
		}
		else if(key == KeyEvent.VK_R) {
			System.out.println("SpecialProjectileBuffer size:" + specialProjectileBuffer.size());
			long startTime = System.nanoTime();
			// projectileList.add(specialProjectileBuffer.pop().setProperties(getPositionX(), getPositionY(), 12, 4));
			// projectileList.add(specialProjectileBuffer.pop().setProperties(getPositionX(), getPositionY(), 12, 2));
			// projectileList.add(specialProjectileBuffer.pop().setProperties(getPositionX(), getPositionY(), 12, 0));
			// projectileList.add(specialProjectileBuffer.pop().setProperties(getPositionX(), getPositionY(), 12, -2));
			// projectileList.add(specialProjectileBuffer.pop().setProperties(getPositionX(), getPositionY(), 12, -4));

			long startTimeInstanciate = System.nanoTime();
			projectileList.add(specialProjectileBuffer.get(bufferIndex).setProperties(getPositionX(), getPositionY(), 12, 4));
			projectileList.add(specialProjectileBuffer.get(bufferIndex+1).setProperties(getPositionX(), getPositionY(), 12, 2));
			projectileList.add(specialProjectileBuffer.get(bufferIndex+2).setProperties(getPositionX(), getPositionY(), 12, 0));
			projectileList.add(specialProjectileBuffer.get(bufferIndex+3).setProperties(getPositionX(), getPositionY(), 12, -2));
			projectileList.add(specialProjectileBuffer.get(bufferIndex+4).setProperties(getPositionX(), getPositionY(), 12, -4));
			long endTimeInstanciate = System.nanoTime();
			Long fireDurationInstanciate = (endTimeInstanciate - startTimeInstanciate);
			System.out.println("instanciate -> exec time: " + fireDurationInstanciate/1000000);
			bufferIndex = bufferIndex + 5;
			// projectileList.add(new SpecialProjectile(getPositionX(), getPositionY(), 12, 4));
			// projectileList.add(new SpecialProjectile(getPositionX(), getPositionY(), 12, 2));
			// projectileList.add(new SpecialProjectile(getPositionX(), getPositionY(), 12, 0));
			// projectileList.add(new SpecialProjectile(getPositionX(), getPositionY(), 12, -2));
			// projectileList.add(new SpecialProjectile(getPositionX(), getPositionY(), 12, -4));
			long startTimeAdd = System.nanoTime();
			// temp.add(p1);
			// temp.add(p2);
			// temp.add(p3);
			// temp.add(p4);
			// temp.add(p5);
			long endTimeAdd = System.nanoTime();
			Long fireDurationAdd = (endTimeAdd - startTimeAdd);
			System.out.println("add -> exec time: " + fireDurationAdd/1000000);

			long endTime = System.nanoTime();
			Long fireDuration = (endTime - startTime);
			System.out.println("player.fireProjectile() -> exec time: " + fireDuration/1000000);

		}
	}
}