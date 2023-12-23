import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Playstate implements State {

	public static boolean left, right, down, up;

	public final static Clip music = music();
	
	@Override
	public void update() {
		if (Tetris.pause || GamePanel.frameCount % GamePanel.level_delay != 0)
			return;
		Tetronomino.update();
		if (!music.isActive()) {
			music.start();
		}
	}

	public static void renderText(Graphics g) {
		g.setColor(Color.white);
		GamePanel.drawString(g, "Score\n" + Tetris.score, 2 * Grid.sqs + Grid.sqs / 2, 3 * Grid.sqs);
		GamePanel.drawString(g, "Level\n" + Level.currlevel, 2 * Grid.sqs + Grid.sqs / 2, 9 * Grid.sqs);
		GamePanel.drawString(g, "Lines cleared\n" + Tetris.linescleared, 21 * Grid.sqs - Grid.sqs / 2, 13 * Grid.sqs);
		if (GameStateManager.getState() == GameStateManager.PLAYSTATE || GameStateManager.getState() == GameStateManager.ANIMATION)
			GamePanel.drawString(g, "Next", 21 * Grid.sqs - Grid.sqs / 2, 3 * Grid.sqs);
	}

	@Override
	public void render(Graphics2D g) {
		Graphics2D g2 = (Graphics2D) g;
		renderText(g);
		Grid.render(g2);
		Tetronomino.render(g2);
		g2.setColor(Color.white);
		if (Tetris.pause)
			GamePanel.drawString(g, "Paused", 11 * Grid.sqs, 3 * Grid.sqs);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!Tetris.pause) {
			if (e.getKeyCode() == KeyEvent.VK_UP && up) {
				Tetronomino.rotateLeft();
				up = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_W && up) {
				Tetronomino.rotateLeft();
				up = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_S && up) {
				Tetronomino.rotateRight();
				up = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT && left) {
				Tetronomino.moveLeft();
				left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && right) {
				Tetronomino.moveRight();
				right = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && down) {
				Tetronomino.moveDown();
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			Tetris.pause = !Tetris.pause;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}

	}

	public static void playSoundeffect(int index) {
		Clip clips[] = new Clip[5];
		File files[] = new File[5];

		files[0] = new File("sounds/Tetris_moving.wav");
		files[1] = new File("sounds/nes-tetris-sound-effect-tetris-clear.wav");
		files[2] = new File("sounds/Tetris_regular_line_clear.wav");
		files[3] = new File("sounds/Tetris_turn_effect.wav");
		files[4] = new File("sounds/fall.wav");

		try {
			for (int i = 0; i < files.length; i++) {
				clips[i] = AudioSystem.getClip();
				clips[i].open(AudioSystem.getAudioInputStream(files[i]));
			}
			clips[index].start();;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static Clip music() {
		try {
			File file = new File("sounds/theme.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			return clip;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
