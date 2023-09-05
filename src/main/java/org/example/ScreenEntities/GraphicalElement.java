package org.example.ScreenEntities;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public abstract class GraphicalElement {

	protected int positionX;
	protected int positionY;
	protected int movementX;
	protected int movementY;
	protected Image image;
	protected int imageWidth;
	protected int imageHeight;

	public abstract void load();


	public void loadImage(String imageName) {
		try {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(imageName + ".png");
		image = ImageIO.read(inputStream);
		} catch(IOException e) {
			e.printStackTrace();
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


