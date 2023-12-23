import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Shape {

	I(new int[][][] { { { 0 }, { 0 }, { 0 }, { 0 } }, { {}, { 0, 1, 2, 3 } }, { { 0 }, { 0 }, { 0 }, { 0 } },
			{ {}, {}, { 0, 1, 2, 3 } } }, "images/Block1.png", 0),
	J(new int[][][] { { { 0, 1 }, { 1 }, { 1 } }, { { 2 }, { 0, 1, 2 } }, { { 0 }, { 0 }, { 0, 1 } },
			{ {}, { 0, 1, 2 }, { 0 } } }, "images/Block3.png", 1),
	L(new int[][][] { { { 1 }, { 1 }, { 0, 1 } }, { {}, { 0 }, { 0, 1, 2 } }, { { 0, 1 }, { 0 }, { 0 } },
			{ { 0, 1, 2 }, { 2 } } }, "images/Block2.png", 2),
	O(new int[][][] { { { 0, 1 }, { 0, 1 } }, { { 0, 1 }, { 0, 1 } }, { { 0, 1 }, { 0, 1 } }, { { 0, 1 }, { 0, 1 } } },
			"images/Block1.png", 3),
	T(new int[][][] { { { 1 }, { 1, 2 }, { 1 } }, { {}, { 0, 1, 2 }, { 1 } }, { { 1 }, { 1, 0 }, { 1 } },
			{ { 1 }, { 0, 1, 2 } } }, "images/Block1.png", 4),
	Z(new int[][][] { { { 0 }, { 0, 1 }, { 1 } }, { {}, { 1, 2 }, { 0, 1 } }, { { 0 }, { 0, 1 }, { 1 } },
			{ {}, { 1, 2 }, { 0, 1 } } }, "images/Block3.png", 5),
	S(new int[][][] { { { 1 }, { 0, 1 }, { 0 } }, { {}, { 0, 1 }, { 1, 2 } }, { { 1 }, { 0, 1 }, { 0 } },
			{ { 0, 1 }, { 1, 2 } } }, "images/Block2.png", 6);

	private final int[][][] rotations;
	private BufferedImage img;
	public static final int length = 7;
	public final int index;

	Shape(int[][][] rotations, String imgpath, int index) {
		this.rotations = rotations;
		try {
			img = ImageIO.read(new File(imgpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img = resizeImage(img, Grid.sqs, Grid.sqs);
		this.index = index;
	}

	public void render(Graphics2D g, int rotation_state, int xoff, int yoff) {
		for (int x = 0; x < getRotation(rotation_state).length; x++)
			for (int y = 0; y < getRotation(rotation_state)[x].length; y++)
				g.drawImage(getTexture(), xoff + x * Grid.sqs, yoff + getRotation(rotation_state)[x][y] * Grid.sqs,
						null);
	}

	public int[][][] getRotations() {
		return rotations;
	}

	public int[][] getRotation(int i) {
		return rotations[i];
	}

	public BufferedImage getTexture() {
		return img;
	}

	public static BufferedImage resizeImage(final Image image, int width, int height) {
		final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D graphics2D = bufferedImage.createGraphics();
		graphics2D.setComposite(AlphaComposite.Src); // below three lines are for RenderingHints for better image
														// quality at cost of higher processing time
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawImage(image, 0, 0, width, height, null);
		graphics2D.dispose();
		return bufferedImage;
	}

}
