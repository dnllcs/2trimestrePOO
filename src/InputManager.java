
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;


public class InputManager implements KeyListener {

	ArrayList<KeyEvent> keysPressed;
	public InputManager() {
		keysPressed = new ArrayList<>();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		keysPressed.add(e);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keysPressed.add(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keysPressed.add(e);
	}

	public void printKeyList() {
		keysPressed.stream().forEach(key -> System.out.println(key));;
	}

}