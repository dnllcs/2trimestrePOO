package org.example.ScreenEntities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Enemy extends GraphicalElement{

	public boolean isDestroyed = false;
	//timer que define a duracao da explosao ate ela ser removida
	private Timer explosionDuration;

	public Enemy(int posX, int posY) {
		this.positionX = posX;
		this.positionY = posY;
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
		explosionDuration = new Timer(500, setDestroyed);
		explosionDuration.setRepeats(false);
		explosionDuration.start();
	}
	//muda o valor de isDestroyed para true e para o timer
    ActionListener setDestroyed = new ActionListener() {
    	@Override
        public void actionPerformed(ActionEvent evt) {
        	isDestroyed = true;
        	explosionDuration.stop();
    	}
    };
}