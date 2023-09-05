package org.example.ScreenEntities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class SpaceDebris extends GraphicalElement{

	private List<String> debris;

	public SpaceDebris(int posX, int posY, int movX) {
		this.positionX = posX;
		this.positionY = posY;
		this.movementX = movX;
		this.debris = new ArrayList<>();
		this.debris.add("asteroid");
		this.debris.add("asteroid2");
		this.debris.add("satellite");
		System.out.println(this.debris);
		this.load();
	}
	@Override
	public void load() {
		super.loadImage(this.debris.get((int) (Math.random() * 3)));
		this.imageWidth = this.image.getWidth(null);
		this.imageHeight = this.image.getHeight(null);
	}

	public void refresh() {
		if(this.getPositionX() < 0) {
			int y = (int) (Math.random() * 768);
			this.setPositionX(1366 + 300);
			this.setPositionY(y);

		}
		else {
			this.setPositionX(this.getPositionX() - this.getMovementX());
		}
	}

	
}