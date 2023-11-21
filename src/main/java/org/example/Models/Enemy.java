package org.example.Models;
import org.example.Screens.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.*;
import javax.swing.Timer;

@Entity
@Table(name = "tb_enemy")
public class Enemy extends GraphicalElement{

	public boolean isDestroyed = false;
	@ManyToOne
	@JoinColumn(name = "id_stage")
	private Stage stage;
	//timer que define a duracao da explosao ate ela ser removida
	@Transient
	private Timer explosionDuration;
	public Enemy() {

	}
	public Enemy(int posX, int posY, Stage stage) {
		this.positionX = posX;
		this.positionY = posY;
		this.stage = stage;
	}
	@Override
	public void load() {
		super.loadImage("enemy");
		this.imageWidth = this.image.getWidth(null);
		this.imageHeight = this.image.getHeight(null);
	}

	//Na colisao, o inimigo tem sua imagem mudada para uma de explosao
	//e um timer comeca, no seu fim o atributo isDestroyed passa a ser true
	//permitindo a remocao da explosao pela funcao void cleanUpMovingEntities() na classe Stage
	public void collision() {
		super.loadImage("expl");
		this.imageWidth = this.image.getWidth(null);
		this.imageHeight = this.image.getHeight(null);
		this.explosionDuration = new Timer(500, setDestroyed);
		this.explosionDuration.setRepeats(false);
		this.explosionDuration.start();
		this.imageHeight = 0;
		this.imageWidth = 0;

	}
	@Transient
	//muda o valor de isDestroyed para true e para o timer
    ActionListener setDestroyed = new ActionListener() {
    	@Override
        public void actionPerformed(ActionEvent evt) {
        	isDestroyed = true;
        	explosionDuration.stop();
    	}
    };
}