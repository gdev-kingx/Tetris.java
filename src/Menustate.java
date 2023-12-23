import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Menustate implements State {

	private static int currbutton = 0;

	private static Button[] buttons = new Button[10];

	public Menustate() {
		for (int i = 0; i < 5; i++)
			buttons[i] = new Button().setPosition(250 + i * 50, 150).setLabel(String.valueOf(i));
		for (int i = 5; i < 10; i++)
			buttons[i] = new Button().setPosition(250 + (i - 5) * 50, 200).setLabel(String.valueOf(i));
	}

	public static Button getButton() {
		return buttons[currbutton];
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics2D g) {
		g.setPaint(new GradientPaint(800, 0, Color.RED, 0, 500, Color.WHITE));
		GamePanel.drawString(g, "Choose Level", 250, 80);
		for (Button button : buttons)
			button.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Level.startlevel = currbutton;
			Level.currlevel = Level.startlevel;
			GamePanel.level_delay = Level.values()[Level.currlevel].getSpeed();
			GameStateManager.setState(GameStateManager.PLAYSTATE);
			System.out.println(GamePanel.level_delay);
		}
		currbutton = e.getKeyCode() == KeyEvent.VK_DOWN && currbutton < 5 ? currbutton + 5
				: e.getKeyCode() == KeyEvent.VK_UP && currbutton >= 5 ? currbutton - 5
						: e.getKeyCode() == KeyEvent.VK_RIGHT && currbutton + 1 != 10 ? currbutton + 1
								: e.getKeyCode() == KeyEvent.VK_LEFT && currbutton - 1 != -1 ? currbutton - 1
										: e.getKeyCode() == KeyEvent.VK_LEFT ? 9
												: e.getKeyCode() == KeyEvent.VK_RIGHT ? 0 : currbutton;
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
