import javax.swing.JFrame;


public class MainScreen extends JFrame {
	private Stage stage = new Stage();
	public MainScreen() {
		stage.setFocusable(true);
		stage.addKeyListener(stage);
		stage.repaint();


	}

	public void semiConstructor() {

		super.add(stage);
		super.setTitle("Jogo do IFPR - Campus Paranavaí");
		super.setSize(1024, 728);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setVisible(true);
	}


	public static void main(String[] args) throws InterruptedException {
		MainScreen mainSc = new MainScreen();
		mainSc.semiConstructor();
		while (true) {
			mainSc.stage.moveEnemiesAndProjectiles();
			mainSc.stage.collision();
			mainSc.repaint();
			Thread.sleep(10);
		}
	}

	
}