package simon;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import guiPractice.components.Action;
import guiPractice.components.Component;

public class Button extends Component implements ButtonInterfaceZhen{
	
	private Color color;
	private Action action;
	private final Color DIM = new Color(105, 105, 105);
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private Color displayColor;
	private boolean highlight;

	public Button() {
		super(0, 0, WIDTH, HEIGHT);
	}
	
	public void highlight() {
		if(color != null) displayColor = color;
		highlight = true;
		update();
	}

	public void dim() {
		displayColor = DIM;
		highlight = false;
		update();
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null) g.setColor(displayColor);
		else g.setColor(Color.gray);
		g.fillOval(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.drawOval(0, 0, WIDTH-1, HEIGHT-1);
		if(highlight){
			g.setColor(Color.white);
			Polygon p = new Polygon();
			
			int s = (int)(5/8.0 * WIDTH);
			int t = (int)(1.0/5*HEIGHT)+4;
			p.addPoint(s-4, t-4);
			p.addPoint(s+7, t-2);
			p.addPoint(s+10, t);
			p.addPoint(s+14, t+10);
			p.addPoint(s+12, t+14);
			p.addPoint(s+8, t+3);
			g.fill(p);
		}
	}
	
	public void act() {
		action.act();
	}
	
	public boolean isHovered(int x, int y) {
		return x > getX() && x < getX()+getWidth() && y > getY() && y<getY()+getHeight();
	}
	
	public void setAction(Action a) {
		this.action = a;
	}
}
