package org.example.ScreenEntities;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class DefaultProjectile extends GraphicalElement {

	private static final int WIDTH_RANGE = 80;
	private static final int HEIGHT_RANGE = 20;


	public DefaultProjectile(int posX, int posY) {
		this.positionX = posX + WIDTH_RANGE;
		this.positionY = posY + HEIGHT_RANGE;
		movementX = 12;
		movementY = 0;
		this.load();
	}
	
	public void load() {
		super.loadImage("projectile");
		this.imageWidth = this.image.getWidth(null);
		this.imageHeight = this.image.getHeight(null);	
	}
}