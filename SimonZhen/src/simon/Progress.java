package simon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Component;

public class Progress extends Component implements ProgressInterfaceZhen{
	
	private int round;
	private int size;
	private String text;
	
	public Progress(int x, int y, int w, int h) {
		super(x, y, w, h);
		update();
	}
	
	public void gameOver(){
		text = "Game Over";
	}

	public void setText(String text){
		this.text = text;
		update();
	}
	
	public void setRound(int round) {
		this.round = round;
		update();
	}

	public void setSequenceSize(int sequence) {
		this.size = sequence;
		update();
	}

	public void update(Graphics2D g) {
		g = clear();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);
		text = "This is round:";
		g.setFont(new Font("Helvetica", Font.PLAIN, 12));
		if (text != null) {
			g.drawString(text, 4, getHeight()-5);			
		}
	}
}
