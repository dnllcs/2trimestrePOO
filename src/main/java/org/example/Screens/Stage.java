package org.example.Screens;

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
import javax.persistence.*;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.example.Models.*;
import org.example.services.StageService;

@Entity
@Table
public class Stage extends JPanel implements IStage, ActionListener, KeyListener{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stage")
	private Integer id;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_elementgrafico")
	private Player player;
	@OneToMany(mappedBy = "stage", cascade=CascadeType.ALL)
	private List<Enemy> enemyList;
	@OneToMany(mappedBy = "stage", cascade=CascadeType.ALL)
	private List<SpaceDebris> backgroundElement;
	@Transient
	private static Image background;
	@Transient
	private static Random rdm = new Random();
	@Transient
	private static final int DELAY = 5;
	@Transient
	private static final int ENEMY_SPAWN_DELAY = 750;
	@Transient
	private static final int MAIN_WINDOW_WIDTH = 1366;
	@Transient
	private static final int MAIN_WINDOW_HEIGHT = 768;
	@Transient
	private static final int SCORE_PER_ENEMY = 10;
	@Transient
	private static final int ENEMY_MOVEMENT = 12;
	@Transient
	private static final int SPAWN_POINT = MAIN_WINDOW_WIDTH + 100;
	@Transient
	private static final int DESPAWN_POINT = -100;
	@Transient
	public static Timer enemySpawTimer;
	@Transient
	public static Timer refreshTimer;
	@Transient
	public int alreadyExists;
	public Stage() {} //Usado pelo Hibernate
	public Stage(int test) { //Quando selecionado um new game
		this.alreadyExists = test;
		setUpStage();
	}
	public Stage(Stage loaded, int test) { //Quando carregando um jogo ja existente
		this.alreadyExists = test;
		this.loadFromDatabase(loaded);
	}
	public void setUpStage() {
		try {
			InputStream backgroundStream = getClass().getClassLoader().getResourceAsStream("background.png");
			background = ImageIO.read(backgroundStream);
		} catch(IOException e) {
			e.printStackTrace();
		}
		this.enemyList = new ArrayList<>();
		this.inializeGraphicElements();
		this.player = new Player();
		this.player.load();
		enemySpawTimer = new Timer(ENEMY_SPAWN_DELAY, enemySpawn);
		refreshTimer = new Timer(DELAY, this);
		enemySpawTimer.start();
		refreshTimer.start();
		addKeyListener(this);
	}
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(background, 0, 0, null);

		backgroundElement.stream().forEach(e -> {
			graphics.drawImage(e.getImage(), e.getPositionX(), e.getPositionY(), this);
		});

		enemyList.stream().forEach(e -> {
			graphics.drawImage(e.getImage(), e.getPositionX(), e.getPositionY(), this);
		});

		player.projectileList.stream().forEach(p -> {
			graphics.drawImage(p.getImage(), p.getPositionX(), p.getPositionY(), this);
		});

		graphics.drawImage(player.getImage(), player.getPositionX(), player.getPositionY(), this);

		for(int i = 0;i<3;i++) {
			if(player.getHitpoints() > i) {
				graphics.drawImage(player.getHitpointImage(), 20+(i*30), 45, this);
			}
			else {
				graphics.drawImage(player.getHitpointEmptyImage(), 20+(i*30), 45, this);
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


    public void moveEntities() {
		for(int i = 0;i<this.enemyList.size();i++) {
			Enemy e = enemyList.get(i);
			if(!e.isDestroyed) {
				e.setPositionX(e.getPositionX() - ENEMY_MOVEMENT);
			}
		}
		if(this.player.projectileList.size() > 0) {
			for(int i = 0;i<this.player.projectileList.size();i++) {
				GraphicalElement p = this.player.projectileList.get(i);
				p.setPositionX(p.getPositionX() + p.getMovementX());
				p.setPositionY(p.getPositionY() + p.getMovementY());
			}
		}
		for(int i = 0;i<this.backgroundElement.size();i++) {
			this.backgroundElement.get(i).refresh();
		}
    }

    //Itera sob todas as entidades de tela e checa por intersecoes
	public void collision() {
		for(int i = 0;i<this.enemyList.size();i++) {
			Enemy en = this.enemyList.get(i);
			if(en.getRectangle().intersects(this.player.getRectangle())) {
				en.collision();
				this.player.setHitpoints(this.player.getHitpoints() - 1);
				en.collision();
				if(this.player.getHitpoints() == 0) {
					this.setVisible(false);
				}
			}
			for(int j = 0;j<this.player.projectileList.size();j++) {
				Projectile proj = this.player.projectileList.get(j);
				if(proj.getRectangle().intersects(en.getRectangle())) {
					en.collision();
					this.player.setScore(this.player.getScore() + SCORE_PER_ENEMY);
					if(proj.getImageName().equals("projectile")) {
						this.player.projectileList.remove(j);
					}
					
				}
			}
		}
	}

	public void inializeGraphicElements() {
		this.backgroundElement = new ArrayList<SpaceDebris>();
	    for (int i = 0; i < 5; i++) {
	        int x = (int) ((Math.random() * 8000) + MAIN_WINDOW_WIDTH);
	        int y = (int) (Math.random() * MAIN_WINDOW_HEIGHT);
	        int speed = (int) ((Math.random() * 10)+3);
	        SpaceDebris asteroid = new SpaceDebris(x, y, speed, this);
	        this.backgroundElement.add(asteroid);
	    }
	}
	public void loadFromDatabase(Stage stage) {
		this.setUpStage();
		this.id = stage.id;
		this.player = stage.getPlayer();
		this.enemyList = stage.getEnemyList();
		this.player.projectileList.forEach(p -> {p.load();});
		this.enemyList.forEach(e -> {e.load();});
		this.backgroundElement.forEach(b -> {b.load();});
	}
	//atualiza o estado o objeto de Stage
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(enemyList);
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
		System.out.println("TYPED");
		if((int)e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(this.alreadyExists == 0) {
				StageService.insert(this);
			}
			else if(this.alreadyExists == 1) {
				StageService.update(this);
			}
			enemySpawTimer.stop();
			refreshTimer.stop();
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
			if(this.enemyList.get(i).isDestroyed || enemyList.get(i).getPositionX() < DESPAWN_POINT) {
				this.enemyList.remove(i);
			}
		}

    	for(int i = 0;i<player.projectileList.size();i++) {
    		if(this.player.projectileList.get(i).getPositionX() > MAIN_WINDOW_WIDTH) {
				this.player.projectileList.remove(i);
    		}
    	}
	}

	//Actionlistener que cria entidades da classe Enemy
	@Transient
    ActionListener enemySpawn = new ActionListener() {
    	@Override
        public void actionPerformed(ActionEvent evt) {
       		Rectangle rec = new Rectangle(SPAWN_POINT, 25 + rdm.nextInt(MAIN_WINDOW_HEIGHT - 25), 60, 60);
       		//Impede que duas entidades compartilhem o mesmo espaco no plano
        	while(enemyList.stream().anyMatch(e -> e.getRectangle().intersects(rec))) {
        		rec.setBounds(SPAWN_POINT, 25 + rdm.nextInt(MAIN_WINDOW_HEIGHT - 25), 60, 60);        	
        	}
			Enemy en = new Enemy(SPAWN_POINT, (int) rec.getY(), Stage.this);
			en.load();
			enemyList.add(en);
    	}	
    };
	public Player getPlayer() {
		return player;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}


}