package org.example.Models;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GraphicalElement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_elementgrafico")
	private Integer id;
	@Column(name = "x_position")
	protected int positionX;
	@Column(name = "y_position")
	protected int positionY;
	protected int movementX;
	protected int movementY;
	@Transient
	protected Image image;
	@Column(name = "image_width")
	protected int imageWidth;
	@Column(name = "image_height")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}


