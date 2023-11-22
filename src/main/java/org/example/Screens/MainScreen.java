package org.example.Screens;

import org.example.services.StageService;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MainScreen extends JFrame  implements KeyListener{
	public int choice;
	public Stage stage;
	public JLabel cursor;
	private static final int MAIN_WINDOW_WIDTH = 1366;
	private static final int MAIN_WINDOW_HEIGHT = 768;

	public MainScreen() {
		this.populateMenu();
		super.addKeyListener(this);
		super.setTitle("Jogo do IFPR - Campus Paranavaí");
		super.setSize(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setVisible(true);
	}
	//Menu
	public void populateMenu() {
		JPanel p = new JPanel();
		JLabel newGame = new JLabel("New Game");
		JLabel title = new JLabel("yetToBeTitled");
		JLabel placeHolder1 = new JLabel("continue");
		JLabel placeHolder2 = new JLabel("placeHolder2");
		JLabel exit = new JLabel("exit");
		cursor = new JLabel(">");


		JLabel keyBindingsInGame = new JLabel("Setas para mover, barra de espaco para atirar, R disparar tiro especial");
		JLabel keyBindingsMenu = new JLabel("seta para cima/baixo para selecionar, ENTER para confirmar, ESC para voltar ao menu");

		newGame.setBounds((MAIN_WINDOW_WIDTH / 2) - 125, 350, 250, 150);
		title.setBounds((MAIN_WINDOW_WIDTH / 2) - 250, 150, 500, 150);
		placeHolder1.setBounds((MAIN_WINDOW_WIDTH / 2) - 125, 400, 450, 150);
		placeHolder2.setBounds((MAIN_WINDOW_WIDTH / 2) - 125, 450, 250, 150);
		cursor.setBounds((MAIN_WINDOW_WIDTH / 2) - 145, 350, 50, 150);
		exit.setBounds((MAIN_WINDOW_WIDTH / 2) - 125, 500, 250, 150);

		keyBindingsInGame.setBounds(200, 550, 600, 300);
		keyBindingsMenu.setBounds(200, 500, 600, 300);

		p.setForeground(Color.BLACK);
		newGame.setForeground(Color.MAGENTA);
		newGame.setFont(new Font("Monospaced", Font.PLAIN, 30));
		title.setForeground(Color.MAGENTA);
		title.setFont(new Font("Monospaced", Font.BOLD, 50));
		placeHolder1.setForeground(Color.MAGENTA);
		placeHolder1.setFont(new Font("Monospaced", Font.PLAIN, 25));
		placeHolder2.setForeground(Color.MAGENTA);
		placeHolder2.setFont(new Font("Monospaced", Font.PLAIN, 25));
		cursor.setForeground(Color.MAGENTA);
		cursor.setFont(new Font("Monospaced", Font.PLAIN, 25));
		exit.setFont(new Font("Monospaced", Font.PLAIN, 25));
		exit.setForeground(Color.MAGENTA);

		p.setLayout(null);
		p.setVisible(true);
		p.add(newGame);
		p.add(placeHolder1);
		p.add(placeHolder2);
		p.add(cursor);
		p.add(title);
		p.add(exit);
		p.add(keyBindingsMenu);
		p.add(keyBindingsInGame);
		add(p);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("public void keyTyped(KeyEvent e)");
	}
	//Navegacao do menu
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_DOWN && this.choice < 3) {
			this.choice += 1;
			cursor.setLocation(cursor.getX(), cursor.getY() + 50);
		}
		else if(key == KeyEvent.VK_UP && this.choice > 0) {
			this.choice -= 1;
			cursor.setLocation(cursor.getX(), cursor.getY() - 50);
		}
		else if(key == KeyEvent.VK_ENTER) {
			if(choice == 0) {
				try {
				stage = new Stage(0);
				stage.addComponentListener(new CustomComponentListener());
				this.getContentPane().removeAll();
				this.getContentPane().add(stage);
				this.validate();
				//this.setVisible(true);
				this.removeKeyListener(this);
				stage.requestFocus(true);
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
			}
			else if(choice == 1) {
				try {
					stage = StageService.continueLastSave();
					stage.addComponentListener(new CustomComponentListener());
					this.getContentPane().removeAll();
					this.getContentPane().add(stage);
					this.validate();
					stage.requestFocus();
					//this.setVisible(true);
					this.removeKeyListener(this);
					stage.requestFocus(true);
					choice = 0;
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
			}
			else if(choice == 2) {

			}
			else if(choice == 3) {
				System.exit(0);
			}
		}
	}
// this.validate();
// this.setVisible(true);
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("public void keyTyped(KeyEvent e)");
	}
	//Listener adicionado ao painel stage, uma vez feito invisivel o metodo componentHidden e chamado
	//remove o painel stage e repopula o menu
   class CustomComponentListener implements ComponentListener {
      public void componentResized(ComponentEvent e) {
         
      }
      public void componentMoved(ComponentEvent e) {
         
      }
      public void componentShown(ComponentEvent e) {
         
      }
      public void componentHidden(ComponentEvent e) {
      	System.out.println("componentHidden");
      	getContentPane().removeAll();
      	populateMenu();
		repaint();
		validate();
		setVisible(true);      
		MainScreen.this.addKeyListener(MainScreen.this);
		MainScreen.this.stage = null;
		requestFocus(true);
		}
   }
}