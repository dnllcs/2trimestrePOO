package org.example;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.example.ScreenEntities.Enemy;
import org.example.ScreenEntities.Player;
import org.example.ScreenEntities.DefaultProjectile;

public class Stage extends JPanel implements ActionListener, KeyListener{
	private Player player;
	private Image background;
	private List<Enemy> enemyList = new ArrayList<>();
	private static Random rdm = new Random();
	private static final int DELAY = 5;
	private static final int ENEMY_SPAWN_DELAY = 250;
	private static final int MAIN_WINDOW_WIDTH = 1366;
	private static final int MAIN_WINDOW_HEIGHT = 768;
	private static int enemyMovement = 8;
	private static int spawnPoint = MAIN_WINDOW_WIDTH + 100;
	private static int despawnPoint = -100;


	public Stage() {
		try {
		InputStream imageStream = getClass().getClassLoader().getResourceAsStream("background.png");
		this.background = ImageIO.read(imageStream);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.player = new Player();
		player.load();
		//Cria objetos da classe Enemy no intervalo estabelecido
		Timer enemySpawTimer = new Timer(ENEMY_SPAWN_DELAY, enemySpawn);
		//Atualiza o stage de acordo com o intervalo estabelecido
		Timer refreshTimer = new Timer(DELAY, this);
		enemySpawTimer.start();
		refreshTimer.start();
		addKeyListener(this);
	}
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(background, 0, 0, null);
		enemyList.stream().forEach(e -> {
			graphics.drawImage(e.getImage(), e.getPositionX(), e.getPositionY(), this);
		});
		player.projectileList.stream().forEach(p -> {
			graphics.drawImage(p.getImage(), p.getPositionX(), p.getPositionY(), this);
		});
		graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);
		g.dispose();

	}
	//Move objetos das classes Enemy e DefaultProjectile
    public void moveEntities() {
    	enemyList.stream().forEach(e -> {
    		if(!e.isDestroyed) {
	    		e.setPositionX(e.getPositionX() - enemyMovement);	
    		}
    	});
		if(player.projectileList.size() > 0) {
			for(int i = 0;i<player.projectileList.size();i++) {
				player.projectileList.get(i).setPositionX(player.projectileList.get(i).getPositionX() + player.projectileList.get(i).getMovementX());
				player.projectileList.get(i).setPositionY(player.projectileList.get(i).getPositionY() + player.projectileList.get(i).getMovementY());
			}
		}
    }
    //Itera sob todas as entidades de tela e checa por intersecoes
	public void collision() {
		for(int i = 0;i<enemyList.size();i++) {
			if(enemyList.get(i).getRectangle().intersects(this.player.getRectangle())) {
				enemyList.get(i).collision();
			}
		}
		for(int i = 0;i<enemyList.size();i++) {
			for(int j = 0;j<player.projectileList.size();j++) {
				if(player.projectileList.get(j).getRectangle().intersects(enemyList.get(i).getRectangle())) {
					enemyList.get(i).collision();
					if(player.projectileList.get(j) instanceof DefaultProjectile) {
					player.projectileList.remove(j);
					}
					
				}
			}
		}

	}
	//atualiza o estado o objeto de Stage
	@Override
	public void actionPerformed(ActionEvent e) {
		this.player.reload();
		this.moveEntities();
		this.collision();
		this.cleanUpMovingEntities();
        repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	//deixa o painel invisivel caso a tecla ESC seja apertada
	//para a movimentacao do player
	@Override
	public void keyPressed(KeyEvent e) {
		if((int)e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.setVisible(false);
		}
		this.player.move(e);
	}

	//para a movimentacao do player
	//atira o projetil
	@Override
	public void keyReleased(KeyEvent e) {
		this.player.stop(e);
		this.player.fireProjectile(e);
	}
	//Remove objetos da lista enemyList que foram destroidos ou estao fora da tela
	//Remove objetos da lista projectileList que estao fora da tela
	public void cleanUpMovingEntities() {
		for(int i = 0;i<enemyList.size();i++) {
			if(enemyList.get(i).isDestroyed || enemyList.get(i).getPositionX() < despawnPoint) {
				enemyList.remove(i);
			}
		}

    	for(int i = 0;i<player.projectileList.size();i++) {
    		if(player.projectileList.get(i).getPositionX() > MAIN_WINDOW_WIDTH) {
    			player.projectileList.remove(i);
    		}
    	}
	}
	//Actionlistener que cria entidades da classe Enemy
    ActionListener enemySpawn = new ActionListener() {
    	@Override
        public void actionPerformed(ActionEvent evt) {
       		Rectangle rec = new Rectangle(spawnPoint, 25 + rdm.nextInt(MAIN_WINDOW_HEIGHT - 25), 60, 60);
       		//Impede que duas entidades compartilhem o mesmo espaco no plano
        	while(enemyList.stream().anyMatch(e -> e.getRectangle().intersects(rec))) {
        		rec.setBounds(spawnPoint, 25 + rdm.nextInt(MAIN_WINDOW_HEIGHT - 25), 60, 60);        	
        	}
			Enemy en = new Enemy(spawnPoint, (int) rec.getY());
			en.load();
			enemyList.add(en);
    	}	
    };
}