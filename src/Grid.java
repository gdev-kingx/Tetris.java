import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Grid {

	public static final int x = 225; // xoffset
	public static final int y = 0; // yoffset
	public static final int nx = 10; // square in x
	public static final int ny = 20; // squares in y
	public static final int sqs = 25; // squaresize

	private final static int NOT_FILLED = 8;

	static int TheGrid[][] = initGrid();
	static int newGrid[][] = initGrid();

	public static void render(Graphics2D g) { // renders grid
		g.setColor(Color.black);
		g.fillRect(19 * sqs + sqs / 2, 5 * sqs, sqs * 6, sqs * 6);
		Shape.values()[Tetronomino.rn].render(g, 0, 20 * sqs + sqs / 2, 7 * sqs); // preview

		for (int x = 0; x < nx; x++)
			for (int y = 0; y < ny; y++) {
				if (TheGrid[x][y] != NOT_FILLED && !Tetris.pause) {
					g.drawImage(Shape.values()[TheGrid[x][y]].getTexture(), Grid.x + sqs * x, Grid.y + sqs * y, null);

				} else
					g.fillRect(Grid.x + x * sqs, Grid.y + y * sqs, sqs, sqs);
			}
	}

	private static int[][] initGrid() {
		int grid[][] = new int[nx][ny];
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[i].length; j++)
				grid[i][j] = NOT_FILLED;
		return grid;
	}

	private static void lock() { // locks piece
		int[][] lockingGrid = Tetronomino.getCurrShape().getRotation(Tetronomino.getRotation_state());
		for (int i = 0; i < lockingGrid.length; i++)
			for (int j = 0; j < lockingGrid[i].length; j++) {
				if (Tetronomino.getX() + i >= 0 && Tetronomino.getY() + lockingGrid[i][j] >= 0) {
					TheGrid[Tetronomino.getX() + i][Tetronomino.getY() + lockingGrid[i][j]] = Tetronomino
							.getCurrShape().index;
				} else {
					Playstate.music.stop();
					GameStateManager.setState(GameStateManager.GAME_OVER);
				}
			}
	}

	public static void endTurn() { // ends turn
		lock();

		Tetris.score += Tetris.linesdragged;
		Tetris.linesdragged = 0;
		ArrayList<Integer> toClearRows = clearRows();
		Playstate.down = false;
		if (toClearRows.size() < 1) {
			Playstate.playSoundeffect(4);
			new Tetronomino();
			return;
		}
		Tetris.linescleared += toClearRows.size();

		for (int x = 0; x < nx; x++)
			for (int y = 0; y < ny; y++)
				newGrid[x][y] = TheGrid[x][y];
		for (int x = 0; x < nx; x++)
			for (int i = 0; i < toClearRows.size(); i++)
				newGrid[x][toClearRows.get(i)] = NOT_FILLED;
		for (int i = 0; i < toClearRows.size(); i++) {
			for (int yy = toClearRows.get(i); yy > 0; yy--)
				for (int xx = 0; xx < nx; xx++)
					newGrid[xx][yy] = newGrid[xx][yy - 1];
		}
		Tetris.score += Level.values()[Level.currlevel].getIncreasment(toClearRows.size());
		if (Tetris.linescleared >= Level.startlevel * 10 + 10 && Tetris.linescleared / 10 > Level.currlevel) {
			Level.currlevel = Tetris.linescleared / 10;
			GamePanel.level_delay = Level.values()[Level.currlevel].getSpeed();
		}
		if (toClearRows.size() == 4) {
			Playstate.playSoundeffect(1);
		} else {
			Playstate.playSoundeffect(2);
		}
		GameStateManager.setState(GameStateManager.ANIMATION);
		Animation.animationcount = 70;
		Animation.rows = toClearRows;
	}

	public static void updateGrid() {
		for (int x = 0; x < nx; x++)
			for (int y = 0; y < ny; y++)
				TheGrid[x][y] = newGrid[x][y];
		new Tetronomino();
	}

	static ArrayList<Integer> clearRows() { // returns all 'full' rows
		ArrayList<Integer> rows = new ArrayList<Integer>();
		for (int y = 0; y < ny; y++) {
			boolean fullrow = true;
			for (int x = 0; x < nx; x++)
				if (Grid.TheGrid[x][y] == NOT_FILLED) {
					fullrow = false;
					break;
				}
			if (fullrow)
				rows.add(y);
		}
		return rows;
	}

	public static boolean occupiedSquare(int x, int y) { // returns whether a square is occupied
		if (y < 0 && x < nx && x >= 0)
			return false;
		return (x >= nx || x < 0 || y >= ny - 1 || TheGrid[x][y] != NOT_FILLED);
	}
}
