package org.example.ScreenEntities;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
//Projetil special, nao atualmente em uso
//atualmente, a logica do ataque especial atrelado a tecla R esta na classe personagem
public class SpecialProjectile extends GraphicalElement {
	private static final int WIDTH_RANGE = 80;
	private static final int HEIGHT_RANGE = 0;

	public SpecialProjectile(int posX, int posY, int movX, int movY) {
		this.positionX = posX + WIDTH_RANGE;
		this.positionY = posY + HEIGHT_RANGE;
		movementX = movX;
		movementY = movY;
		this.load();
	}
	@Override
	public void load() {
		super.loadImage("specialProjectile");
		imageWidth = this.image.getWidth(null);
		imageHeight = this.image.getHeight(null);
	}
}