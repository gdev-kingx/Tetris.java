import javax.swing.JFrame;

public class Tetris extends JFrame {

	private static final long serialVersionUID = 1L;

	public static int score;
	public static short linescleared;
	public static short linesdragged;

	public static boolean pause = false;

	Tetris() {
		super("Tetris in Java");
		setSize(805, 500);
		add(new GamePanel());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {
		new Tetris();
	}

}
