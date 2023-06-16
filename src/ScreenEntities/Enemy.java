package ScreenEntities;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Enemy {

	private int positionX;
	private int positionY;
	private int movementX;
	private int movementY;
	private Image image;
	private int imageWidth;
	private int imageHeight;
	public boolean isDestroyed = false;
	private Timer explosionDuration;

	public Enemy(int posX, int posY) {
		this.positionX = posX;
		this.positionY = posY;
	}

	public void load() {
		ImageIcon loading = new ImageIcon("assets/enemy.png");
		this.image = loading.getImage();
		this.imageWidth = this.image.getWidth(null);
		this.imageHeight = this.image.getHeight(null);
	}

	public void collision() {
		ImageIcon loading = new ImageIcon("assets/expl.png");
		this.image = loading.getImage();
		this.imageWidth = this.image.getWidth(null);
		this.imageHeight = this.image.getHeight(null);
		//explosionDuration = new Timer(2000, setDestroyed);
		this.isDestroyed = true;
	}

    // ActionListener setDestroyed = new ActionListener() {
    //     public void actionPerformed(ActionEvent evt) {
    //     	this.isDestroyed = true;
    // 	}
    // };

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