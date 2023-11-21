package org.example.Models;

import javax.persistence.*;

//Projetil special, nao atualmente em uso
//atualmente, a logica do ataque especial atrelado a tecla R esta na classe personagem
@Entity
public class SpecialProjectile extends GraphicalElement {
	private static final int WIDTH_RANGE = 80;
	private static final int HEIGHT_RANGE = 0;

	public SpecialProjectile() {
		super();
	}

	public SpecialProjectile(int posX, int posY, int movX, int movY) {
		this.positionX = posX + WIDTH_RANGE;
		this.positionY = posY + HEIGHT_RANGE;
		this.movementX = movX;
		this.movementY = movY;
		this.load();
	}
	@Override
	public void load() {
		super.loadImage("specialProjectile");
		this.imageWidth = this.image.getWidth(null);
		this.imageHeight = this.image.getHeight(null);
	}
}