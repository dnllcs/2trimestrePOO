package ScreenEntities;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Projectile {
	private int positionX;
	private int positionY;
	private int movementX;
	private int movementY;
	private Image image;
	private int imageWidth;
	private int imageHeight;
	private static final int WIDTH_RANGE = 120;
	private static final int HEIGHT_RANGE = 120;

	public Projectile(int posX, int posY) {
		this.positionX = posX + WIDTH_RANGE;
		this.positionY = posY + HEIGHT_RANGE;
		this.load();
	}
	public void load() {
		ImageIcon loading = new ImageIcon("assets/projectile.png");
		this.image = loading.getImage();
		this.imageWidth = this.image.getWidth(null);
		this.imageHeight = this.image.getHeight(null);
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