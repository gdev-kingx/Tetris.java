import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Animation implements State {
	static int animationcount;

	static ArrayList<Integer> rows;

	@Override
	public void update() {
		animationcount--;
		if (animationcount < 1) {
			Grid.updateGrid();
			GameStateManager.setState(GameStateManager.PLAYSTATE);
		}

	}

	@Override
	public void render(Graphics2D g) {
		new Playstate().render(g);
		g.setColor(new Color(animationcount * 2, animationcount * 2, animationcount * 2));
		if (rows.size() == 4)
			g.setColor(new Color(animationcount * 3, new Random().nextInt(25), new Random().nextInt(75)));
		for (Integer i : rows)
			g.fillRect(Grid.x, i * Grid.sqs, Grid.nx * Grid.sqs, Grid.sqs);

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
