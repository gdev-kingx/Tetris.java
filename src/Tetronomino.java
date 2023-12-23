import java.awt.Graphics2D;
import java.util.Random;

public class Tetronomino {
	private static Shape shape;
	private static int x, y;
	public static int rn = new Random().nextInt(Shape.length);

	private static int rotation_state = 0;

	public Tetronomino() {
		new Tetronomino(Shape.values()[rn]);
		rn = new Random().nextInt(Shape.length);
	}

	private Tetronomino(Shape shape) {
		Tetronomino.shape = shape;
		x = 3;
		y = -1;
		rotation_state = 0;
	}

	public static Shape getCurrShape() {
		return shape;
	}

	public static int getRotation_state() {
		return rotation_state;
	}

	public static void moveDown() {
		if (isLegal(shape.getRotation(rotation_state), getX(), getY() + 1)) {
			y = getY() + 1;
			Tetris.linesdragged++;
		} else 
			Grid.endTurn();
	}

	public static void moveLeft() {
		if (isLegal(shape.getRotation(rotation_state), getX() - 1, getY())) {
			Playstate.playSoundeffect(0);
			x = getX() - 1;
		}
	}

	public static void moveRight() {
		if (isLegal(shape.getRotation(rotation_state), getX() + 1, getY())) {
			Playstate.playSoundeffect(0);
			x = getX() + 1;
		}
	}

	public static boolean isLegal(int[][] grid, int x, int y) {
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[i].length; j++) {
				if (Grid.occupiedSquare(x + i, y + grid[i][j]))
					return false;
			}
		return true;
	}

	public static void rotateRight() {
		final int next_rotation_state = rotation_state == 3 ? 0 : rotation_state + 1;
		if (isLegal(shape.getRotation(next_rotation_state), getX(), getY())) {
			Playstate.playSoundeffect(3);
			rotation_state = next_rotation_state;
		}
	}

	public static void rotateLeft() {
		final int next_rotation_state = rotation_state == 0 ? 3 : rotation_state - 1;
		if (isLegal(shape.getRotation(next_rotation_state), getX(), getY())) {
			Playstate.playSoundeffect(3);
			rotation_state = next_rotation_state;
		}
	}

	public static void update() {
		if (isLegal(shape.getRotation(rotation_state), getX(), getY() + 1)) {
			y = getY() + 1;
		} else 
			Grid.endTurn();

	}

	public static void render(Graphics2D g) {
		Tetronomino.shape.render(g, rotation_state, Grid.x + getX() * Grid.sqs, Grid.y + getY() * Grid.sqs);
	}

	public static int getY() {
		return y;
	}

	public static int getX() {
		return x;
	}
}
