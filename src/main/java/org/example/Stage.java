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
import org.example.ScreenEntities.SpecialProjectile;
import org.example.ScreenEntities.DefaultProjectile;
import org.example.ScreenEntities.GraphicalElement;

public class Stage extends JPanel implements ActionListener, KeyListener{
	private Player player;
	private Image background;
	private Image hitpoint;
	private Image hitPointEmpty;
	private List<Enemy> enemyList = new ArrayList<>();
	private static Random rdm = new Random();
	private static final int DELAY = 5;
	private static final int ENEMY_SPAWN_DELAY = 200;
	private static final int MAIN_WINDOW_WIDTH = 1366;
	private static final int MAIN_WINDOW_HEIGHT = 768;
	private static final int SCORE_PER_ENEMY = 10;
	private static int enemyMovement = 16;
	private static int spawnPoint = MAIN_WINDOW_WIDTH + 100;
	private static int despawnPoint = -100;
	private static Timer enemySpawTimer;
	private static Timer refreshTimer;

	private long moveEntitiesDuration;
	private long collisionDuration;

	private Long runningTime;
	private Long runningTimeStart;

	public Stage() {
		try {
		InputStream backgroundStream = getClass().getClassLoader().getResourceAsStream("background.png");
		InputStream hitpointStream = getClass().getClassLoader().getResourceAsStream("hitpoint.png");
		InputStream hitpointEmptyStream = getClass().getClassLoader().getResourceAsStream("hitpointEmpty.png");
		this.hitpoint = ImageIO.read(hitpointStream);
		this.background = ImageIO.read(backgroundStream);
		this.hitPointEmpty = ImageIO.read(hitpointEmptyStream);

		} catch(IOException e) {
			e.printStackTrace();
		}
		//Teste
		runningTimeStart = System.currentTimeMillis();

		this.player = new Player();
		player.load();
		//Cria objetos da classe Enemy no intervalo estabelecido
		enemySpawTimer = new Timer(ENEMY_SPAWN_DELAY, enemySpawn);
		//Atualiza o stage de acordo com o intervalo estabelecido
		refreshTimer = new Timer(DELAY, this);
		//enemySpawTimer.start();
		refreshTimer.start();
		addKeyListener(this);

	}
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(background, 0, 0, null);
		enemyList.stream().forEach(e -> {
			graphics.drawImage(e.getImage(), e.getPositionX(), e.getPositionY(), this);
		});
		// player.projectileList.stream().forEach(p -> {
		// 	graphics.drawImage(p.getImage(), p.getPositionX(), p.getPositionY(), this);
		// });
		graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);
		for(int i = 0;i<3;i++) {
			if(player.getHitpoints() > i) {
				graphics.drawImage(hitpoint, 20+(i*30), 45, this);
			}
			else {
				graphics.drawImage(hitPointEmpty, 20+(i*30), 45, this);
			}
		}

		this.drawScore(graphics);
		g.dispose();

	}

	public void drawScore(Graphics2D g) {
	    String scoreText = "PONTOS: " + player.getScore();
	    g.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 22));
	    g.setColor(new java.awt.Color(255, 255, 255));
	    g.drawString(scoreText, 20, 25);
	}

	//Move objetos das classes Enemy e DefaultProjectile
    public void moveEntities() {
    	long startTime = System.nanoTime();
		for(int i = 0;i<enemyList.size();i++) {
			Enemy e = enemyList.get(i);
			if(!e.isDestroyed) {
				e.setPositionX(e.getPositionX() - enemyMovement);
			}
		}
		if(player.projectileList.size() > 0) {
			for(int i = 0;i<player.projectileList.size();i++) {
				GraphicalElement p = player.projectileList.get(i);
				p.setPositionX(p.getPositionX() + p.getMovementX());
				p.setPositionY(p.getPositionY() + p.getMovementY());
			}
		}
		long endTime = System.nanoTime();
		moveEntitiesDuration += (endTime - startTime);
    }

    //Itera sob todas as entidades de tela e checa por intersecoes
	public void collision() {
		long startTime = System.nanoTime();
		for(int i = 0;i<enemyList.size();i++) {
			Enemy en = enemyList.get(i);
			if(en.getRectangle().intersects(this.player.getRectangle())) {
				en.collision();
				player.setHitpoints(player.getHitpoints() - 1);
				en.collision();
				if(player.getHitpoints() == 0) {
					this.setVisible(false);
				}
			}
			for(int j = 0;j<player.projectileList.size();j++) {
				GraphicalElement proj = player.projectileList.get(j);
				if(proj.getRectangle().intersects(en.getRectangle())) {
					en.collision();
					player.setScore(player.getScore() + SCORE_PER_ENEMY);
					if(proj instanceof DefaultProjectile) {
						player.projectileList.remove(j);
					}
					
				}
			}
		}
		long endTime = System.nanoTime();
		collisionDuration += (endTime - startTime);

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
			long endTime = System.currentTimeMillis();
			runningTime = endTime - runningTimeStart;
			System.out.println("main() -> exec time: " + this.runningTime);			
			System.out.println("moveEntities() -> exec time: " + this.moveEntitiesDuration/this.runningTime);
			System.out.println("collison() -> exec time: " + this.collisionDuration/this.runningTime);
			//System.out.println("moveEntities() -> exec time: " + this.moveEntitiesDuration);

			this.enemySpawTimer.stop();
			this.refreshTimer.stop();
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
    			if(player.projectileList.get(i) instanceof SpecialProjectile) {
    				SpecialProjectile sp = (SpecialProjectile) player.projectileList.get(i);
    				player.specialProjectileBuffer.push(sp);
    			}
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