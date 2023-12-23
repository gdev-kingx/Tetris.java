import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class GameStateManager {

	public static final int PLAYSTATE = 0;

	public static final int MENUSTATE = 1;
	
	public static final int ANIMATION = 2;
	
	public static final int GAME_OVER = 3;

	private static int state = MENUSTATE;
	private static State[] states;

	public GameStateManager() {
		states = new State[4];

		states[0] = new Playstate();
		states[1] = new Menustate();
		states[2] = new Animation();
		states[3] = new Gameover();
	}

	public static void update() {
		states[getState()].update();
	}

	public static void render(Graphics2D g) {
		states[getState()].render(g);
	}

	public static void keyPressed(KeyEvent e) {
		states[getState()].keyPressed(e);
	}

	public static void keyReleased(KeyEvent e) {
		states[getState()].keyReleased(e);
	}

	public static void setState(int state) {
		GameStateManager.state = state;
	}

	public static int getState() {
		return state;
	}
}
