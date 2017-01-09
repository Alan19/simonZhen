package simon;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Action;
import guiPractice.components.Component;

public class Button extends Component implements ButtonInterfaceZhen{
	
	private Color color;
	private Action action;
	private final Color DIM = new Color(105, 105, 105);
	private int x;
	private int y;

	public Button(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void highlight() {
		this.color = color;
		update();
	}

	public void dim() {
		this.color = color;
		update();
	}

	public void setX(int i) {
		this.x = i;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setY(int i) {
		this.y = i;
	}
	
	public void update(Graphics2D g) {
		g.setColor(this.color);
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	
	public void act() {
		action.act();
	}
	
	public boolean isHovered(int x, int y) {
		return x > getX() && x < getX()+getWidth() && y > getY() && y<getY()+getHeight();
	}
	
	public void setAction(Action a) {
		this.action = action;
	}
}
