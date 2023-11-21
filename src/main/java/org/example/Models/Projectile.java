package org.example.Models;

import javax.persistence.*;

//Projetil special, nao atualmente em uso
//atualmente, a logica do ataque especial atrelado a tecla R esta na classe personagem
@Entity
@Table(name = "tb_projectile")
public class Projectile extends GraphicalElement {
    @ManyToOne
    @JoinColumn(name = "id")
    private Player player;
    private static final int WIDTH_RANGE_SPECIAL = 80;
    private static final int HEIGHT_RANGE_SPECIAL = 0;
    private static final int WIDTH_RANGE_DEFAULT = 80;
    private static final int HEIGHT_RANGE_DEFAULT = 20;

    private String imageName;

    public Projectile() {
        super();
    }

    public Projectile(int posX, int posY, int movX, int movY, Player player) {
        this.positionX = posX + WIDTH_RANGE_SPECIAL;
        this.positionY = posY + HEIGHT_RANGE_SPECIAL;
        this.movementX = movX;
        this.movementY = movY;
        this.imageName = "specialProjectile";
        this.player = player;
        this.load();
    }
    public Projectile(int posX, int posY, Player player) {
        this.positionX = posX + WIDTH_RANGE_DEFAULT;
        this.positionY = posY + HEIGHT_RANGE_DEFAULT;
        movementX = 12;
        movementY = 0;
        this.player = player;
        this.imageName = "projectile";
        this.load();
    }
    @Override
    public void load() {
        super.loadImage(this.imageName);
        this.imageWidth = this.image.getWidth(null);
        this.imageHeight = this.image.getHeight(null);
    }
}