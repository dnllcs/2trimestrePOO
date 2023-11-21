package org.example.Models;

import org.example.Screens.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.*;

import java.awt.Image;

@Entity
@Table(name = "tb_player")
public class Player extends GraphicalElement {

	@OneToMany(mappedBy = "player", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Projectile> projectileList = new ArrayList<>();
	@Column(name = "score")
	private int score;
	private int hitpoints = 3;
	@Transient
	private Image hitpointImage;
	@Transient
	private Image hitpointEmptyImage;
	@OneToOne(mappedBy = "player")
	private Stage stage;
	public static int MOVEMENT = 5;
	private static final int STARTING_POSITION_X = 100;
	private static final int STARTING_POSITION_Y = 100;




	public Player() {
		this.positionX = STARTING_POSITION_X;
		this.positionY = STARTING_POSITION_Y;
	}

	public void load() {
		super.loadImage("player2");
		this.imageWidth = image.getWidth(null);
		this.imageHeight = image.getHeight(null);
		try {
		InputStream hitpointStream = getClass().getClassLoader().getResourceAsStream("hitpoint.png");
		InputStream hitpointEmptyStream = getClass().getClassLoader().getResourceAsStream("hitpointEmpty.png");
		this.hitpointImage = ImageIO.read(hitpointStream);
		this.hitpointEmptyImage = ImageIO.read(hitpointEmptyStream);

		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	public void reload() {

		int tempPositionX = positionX + movementX;
		int tempPositionY = positionY + movementY;
		if(tempPositionX > 1366 - super.imageWidth || tempPositionX < 0) {
			return;
		}
		if(tempPositionY > 768 - super.imageHeight*2 || tempPositionY < 0) {
			return;
		}
		this.positionX = tempPositionX;
		this.positionY = tempPositionY;

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
	public Image getHitpointImage() {
		return this.hitpointImage;
	}

	public Image getHitpointEmptyImage() {
		return this.hitpointEmptyImage;
	}
	
	//logica de movimentacao do personagem
	public void move(KeyEvent e) {
	    int key = e.getKeyCode();
	    switch (key) {
	        case KeyEvent.VK_UP:
				this.movementY = -MOVEMENT;
	            break;
	        case KeyEvent.VK_DOWN:
				this.movementY = MOVEMENT;
	            break;
	        case KeyEvent.VK_LEFT:
				this.movementX = -MOVEMENT;
	            break;
	        case KeyEvent.VK_RIGHT:
				this.movementX = MOVEMENT;
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
				this.movementY = 0;
	            break;
	        case KeyEvent.VK_DOWN:
				this.movementY = 0;
	            break;
	        case KeyEvent.VK_LEFT:
				this.movementX = 0;
	            break;
	        case KeyEvent.VK_RIGHT:
				this.movementX = 0;
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
			projectileList.add(new Projectile(getPositionX(), getPositionY(), this));
		}
		else if(key == KeyEvent.VK_R) {
			projectileList.add(new Projectile(getPositionX(), getPositionY(), 12, 4, this));
			projectileList.add(new Projectile(getPositionX(), getPositionY(), 12, 0, this));
			projectileList.add(new Projectile(getPositionX(), getPositionY(), 12, -4, this));
		}
	}
}