import java.awt.Color;
import sun.audio.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	public static Font Tetris_Font;
	
	public static int frameCount = 0; 
	
	public static int level_delay = 0;

	public static Timer t = null;

	GamePanel() {
		addKeyListener(this);
		setBackground(Color.darkGray);
		setFocusable(true);
		requestFocus();

		try {
			Tetris_Font = Font.createFont(Font.TRUETYPE_FONT, new File("data/04B_30__.TTF")).deriveFont(25f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		new GameStateManager();
		t = new Timer(1000 / 60, this);
		new Tetronomino();

	}

	public static void drawString(Graphics g, String text, int x, int y) {
		final int lineHeight = g.getFontMetrics().getHeight();
		for (String line : text.split("\n"))
			g.drawString(line, x, y += lineHeight);
	}

	private void renderBackground(Graphics2D g) {
		g.setColor(Color.black);
		g.setFont(Tetris_Font);
		for (int x = 0; x < 500 / Grid.sqs; x++)
			for (int y = 0; y < 800 / Grid.sqs; y++)
				if (y % 2 == 0)
					g.drawRect(x * Grid.sqs * 2, y * Grid.sqs, Grid.sqs * 2, Grid.sqs);
				else {
					g.drawRect(-1 * Grid.sqs * 2 + Grid.sqs, y * Grid.sqs, Grid.sqs * 2, Grid.sqs);
					g.drawRect(x * Grid.sqs * 2 + Grid.sqs, y * Grid.sqs, Grid.sqs * 2, Grid.sqs);
				}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		renderBackground(g2);
		GameStateManager.render(g2);
		t.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		GameStateManager.keyPressed(e);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		GameStateManager.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GameStateManager.update();
		frameCount++;
		repaint();
	}


	

}
