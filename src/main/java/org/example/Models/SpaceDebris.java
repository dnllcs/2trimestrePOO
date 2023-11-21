package org.example.Models;

import org.example.Screens.Stage;

import java.util.Arrays;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tb_space_debris")
public class SpaceDebris extends GraphicalElement{
	@Transient
	private static final List<String> DEBRIS_IMAGES = Arrays.asList(
			"asteroid", "asteroid2", "satellite");
	@ManyToOne
	@JoinColumn(name = "id")
	private Stage stage;

	public SpaceDebris(int posX, int posY, int movX, Stage stage) {
		this.positionX = posX;
		this.positionY = posY;
		this.movementX = movX;
		this.stage = stage;
		this.load();
	}
	@Override
	public void load() {
		super.loadImage(DEBRIS_IMAGES.get((int) (Math.random() * 3)));
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