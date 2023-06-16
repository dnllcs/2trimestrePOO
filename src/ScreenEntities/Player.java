package ScreenEntities;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Player {
	private int positionX;
	private int positionY;
	private int movementX;
	private int movementY;
	private Image image;
	private int imageWidth;
	private int imageHeight;
	private static final int MOVEMENT = 3;
	private static final int STARTING_POSITION_X = 100;
	private static final int STARTING_POSITION_Y = 100;

	public Player() {
		this.positionX = STARTING_POSITION_X;
		this.positionY = STARTING_POSITION_Y;
	}

	public void load() {
		ImageIcon loading = new ImageIcon("assets/player.png");
		this.image = loading.getImage();
		this.imageWidth = this.image.getWidth(null);
		this.imageHeight = this.image.getHeight(null);
	}
	public void reload() {
		this.positionX += this.movementX;
		this.positionY += this.movementY;
	}
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
	public void stop(KeyEvent tecla) {
	    int key = tecla.getKeyCode();
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
	public Rectangle getRectangle() {
		return new Rectangle(this.positionX, this.positionY, this.imageWidth, this.imageHeight);
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getMovementX() {
		return movementX;
	}

	public void setMovementX(int movementX) {
		this.movementX = movementX;
	}

	public int getMovementY() {
		return movementY;
	}

	public void setMovementY(int movementY) {
		this.movementY = movementY;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

}