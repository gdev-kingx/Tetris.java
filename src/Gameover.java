import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Gameover implements State {

	private boolean end = false;

	@Override
	public void update() {
		Playstate.music.stop();
		if (GamePanel.frameCount % 5 != 0)
			return;
		boolean end = true;
		for (int y = 0; y < Grid.ny; y++) {
			for (int x = 0; x < Grid.nx; x++)
				if (Grid.TheGrid[x][y] != 8) {
					Grid.TheGrid[x][y] = 8;
					end = false;
				}
			if (!end)
				return;
		}
	}

	@Override
	public void render(Graphics2D g) {
		Playstate.renderText(g);
		for (int y = 0; y < Grid.ny; y++)
			for (int x = 0; x < Grid.nx; x++)
				if (Grid.TheGrid[x][y] != 8) {
					Grid.render(g);
					return;
				}
		end = true;
		GamePanel.drawString(g, "Game Over\nAny key to restart", 370, 70);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!end)
			return;
		GameStateManager.setState(GameStateManager.MENUSTATE);
		Tetris.score = 0;
		Tetris.linescleared = 0;
		Tetris.linesdragged = 0;
		Tetris.pause = false;
		GamePanel.frameCount = 0;
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
