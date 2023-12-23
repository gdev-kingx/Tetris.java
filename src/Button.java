import java.awt.Color;
import java.awt.Graphics2D;

public class Button {
	private int x = 100;
	private int y = 100;
	private int sizex = 40;
	private int sizey = 40;
	private Color c = Color.gray;
	private Color stroke = Color.white;
	private Color labelc = Color.white;
	private String label = "";

	public void render(Graphics2D g) {
		g.setColor(c);
		if (this == Menustate.getButton())
			g.setColor(Color.darkGray);
		g.fillRect(x, y, sizex, sizey);
		g.setColor(stroke);
		g.drawRect(x, y, sizex, sizey);
		g.setColor(labelc);
		GamePanel.drawString(g, label, x + sizex / 4, y);
	}

	public Button setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Button setSize(int x, int y) {
		sizex = x;
		sizey = y;
		return this;
	}

	public Button setColor(Color c) {
		this.c = c;
		return this;
	}

	public Button setLabel(String label) {
		this.label = label;
		return this;
	}

}
