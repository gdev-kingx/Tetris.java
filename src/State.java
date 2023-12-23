import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public interface State {	
		public abstract void update();
		public abstract void render(Graphics2D g);
		public abstract void keyPressed(KeyEvent e);
		public abstract void keyReleased(KeyEvent e);
}
