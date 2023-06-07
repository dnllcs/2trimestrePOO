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

import ScreenEntities.Enemy;
import ScreenEntities.Player;
import ScreenEntities.Projectile;


public class Stage extends JPanel implements KeyListener{
	private Player player;
	private Image background;
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private ArrayList<Enemy> destroyedEnemyList = new ArrayList<>();
	private ArrayList<Projectile> projectileList = new ArrayList<>();
	private Random rdm = new Random();

	public Stage() {
		ImageIcon loading = new ImageIcon("assets/background.png");
		this.background = loading.getImage();
		this.player = new Player();
		player.load();
		System.out.println("FFFFFFFFFFFF");
		Timer timer = new Timer(1000, cleanUpDestroyed);
		Timer enemySpawnTimer = new Timer(500, enemySpawn);
		enemySpawnTimer.start();
		timer.start();	
	}
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(background, 0, 0, null);
		enemyList.stream().forEach(e -> {
			graphics.drawImage(e.getImage(), e.getPositionX(), e.getPositionY(), this);
		});
		destroyedEnemyList.stream().forEach(d -> {
			graphics.drawImage(d.getImage(), d.getPositionX(), d.getPositionY(), this);
		});
		projectileList.stream().forEach(p -> {
			graphics.drawImage(p.getImage(), p.getPositionX(), p.getPositionY(), this);
		});
		graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);
		g.dispose();

	}
    public void moveEntities() {
    	player.setPositionX(player.getPositionX() + player.getMovementX());
    	player.setPositionY(player.getPositionY() + player.getMovementY());
    	enemyList.stream().forEach(e -> {
    		if(!e.isDestroyed) {
	    		e.setPositionX(e.getPositionX()-1);	
    		}
    	});
    	if(projectileList.size() > 0) {
			projectileList.stream().forEach(p -> {
				p.setPositionX(p.getPositionX() + 4);
    		});
		}
    }
    public void fireProjectile() {
    	Projectile p = new Projectile(player.getPositionX(), player.getPositionY());
    	p.load();
    	projectileList.add(p);
    }
	public void collision() {
		System.out.println("collision");
		for(int i = 0;i<enemyList.size();i++) {
			if(enemyList.get(i).getRectangle().intersects(this.player.getRectangle())) {
				enemyList.get(i).collision();
				destroyedEnemyList.add(enemyList.get(i));
			}
		}
		for(int i = 0;i<enemyList.size();i++) {
			for(int j = 0;j<projectileList.size();j++) {
				if(projectileList.get(j).getRectangle().intersects(enemyList.get(i).getRectangle())) {
					enemyList.get(i).collision();
					destroyedEnemyList.add(enemyList.get(i));
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.setMovementY(-5);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.setMovementY(5);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setMovementX(-5);;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setMovementX(5);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.fireProjectile();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.setMovementY(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.setMovementY(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setMovementX(0);;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setMovementX(0);
		}
	}
	public void cleanUpMovingEntities() {
		System.out.println("cleanUpMovingEntities");
		// for(int i = 0;i<enemyList.size();i++) {
    	// 	if(enemyList.get(i).getPositionX() < 100 || enemyList.get(i).isDestroyed) {
    	// 		enemyList.remove(i);
    	// 	}
    	// }
		for(int i = 0;i<enemyList.size();i++) {
			if(enemyList.get(i).isDestroyed) {
				enemyList.remove(i);
			}
		}
    	for(int i = 0;i<projectileList.size();i++) {
    		if(projectileList.get(i).getPositionX() > 1000) {
    			projectileList.remove(i);
    		}
    	}
	}

    ActionListener cleanUpDestroyed = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	for(int i = 0;i<destroyedEnemyList.size();i++) {
        		destroyedEnemyList.remove(i);
        	}
    	}
    };

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