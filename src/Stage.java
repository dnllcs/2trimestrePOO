import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.*;

import ScreenEntities.Enemy;
import ScreenEntities.Player;
import ScreenEntities.Projectile;

public class Stage extends JPanel implements ActionListener, KeyListener{
	private Player player;
	private Image background;
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private ArrayList<Projectile> projectileList = new ArrayList<>();
	private static Random rdm = new Random();
	private static final int DELAY = 5;
	private static final int ENEMY_SPAWN_DELAY = 500;
	private static int enemyMovement = 4;
	private static int bulletSpeed = 6;
	private static int spawnPoint = 1200;
	private static int despawnPoint = -100;

	public Stage() {
		ImageIcon loading = new ImageIcon("assets/background.png");
		this.background = loading.getImage();
		this.player = new Player();
		player.load();
		Timer enemySpawTimer = new Timer(ENEMY_SPAWN_DELAY, enemySpawn);
		Timer refreshTimer = new Timer(DELAY, this);
		enemySpawTimer.start();
		refreshTimer.start();
	}
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(background, 0, 0, null);
		enemyList.stream().forEach(e -> {
			graphics.drawImage(e.getImage(), e.getPositionX(), e.getPositionY(), this);
		});
		projectileList.stream().forEach(p -> {
			graphics.drawImage(p.getImage(), p.getPositionX(), p.getPositionY(), this);
		});
		graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);
		g.dispose();

	}
    public void moveEntities() {
    	enemyList.stream().forEach(e -> {
    		if(!e.isDestroyed) {
	    		e.setPositionX(e.getPositionX()-enemyMovement);	
    		}
    	});
    	if(projectileList.size() > 0) {
			projectileList.stream().forEach(p -> {
				p.setPositionX(p.getPositionX() + bulletSpeed);
    		});
		}
    }
	public void collision() {
		for(int i = 0;i<enemyList.size();i++) {
			if(enemyList.get(i).getRectangle().intersects(this.player.getRectangle())) {
				enemyList.get(i).collision();
				
			}
		}
		for(int i = 0;i<enemyList.size();i++) {
			for(int j = 0;j<projectileList.size();j++) {
				if(projectileList.get(j).getRectangle().intersects(enemyList.get(i).getRectangle())) {
					enemyList.get(i).collision();
					
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {;
		this.player.reload();
		this.moveEntities();
		this.collision();
		this.cleanUpMovingEntities();
        repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.player.move(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.player.stop(e);
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			projectileList.add(new Projectile(player.getPositionX(), player.getPositionY()));
		}
	}

	public void cleanUpMovingEntities() {
		for(int i = 0;i<enemyList.size();i++) {
			if(enemyList.get(i).isDestroyed || enemyList.get(i).getPositionX() < -100) {
				enemyList.remove(i);
			}
		}
    	for(int i = 0;i<projectileList.size();i++) {
    		if(projectileList.get(i).getPositionX() > 1000) {
    			projectileList.remove(i);
    		}
    	}
	}

    ActionListener enemySpawn = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
       		int posY = 50 + rdm.nextInt(600);
       		Rectangle rec = new Rectangle(1200, posY, 60, 60);
       		boolean b = false;
        	for(int i = 0;i<enemyList.size();i++) {
        		if(enemyList.get(i).getRectangle().intersects(rec)) {
        			b = true;
		        	break;
        		}
    		}
    		if(!b) {
    			Enemy en = new Enemy(1200, posY);
    			en.load();
    			enemyList.add(en);
    		}
    	}
    };
}