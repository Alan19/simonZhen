package simon;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Component;

public class Progress extends Component implements ProgressInterfaceZhen{
	
	private static final int WIDTH = 120;
	private static final int HEIGHT = 50;
	
	private boolean gameOver;
	private String roundMessage;
	private String sequenceLength;
	
	public Progress() {
		super(125,200,WIDTH,HEIGHT);
		update();
	}
	
	public void gameOver(){
		gameOver = true;
		update();
	}
	
	public void setRound(int round) {
		roundMessage = "Round " + round;
		update();
	}

	public void setSequenceSize(int sequence) {
		sequenceLength = "Sequence Length : " + sequence;
		update();
	}

	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		if(gameOver){
			g.setColor(new Color(255,55,90));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.white);
			String go = "GAME OVER!";
			g.drawString(go, (WIDTH - fm.stringWidth(go))/2, 20);
			g.drawString(sequenceLength, (WIDTH - fm.stringWidth(sequenceLength))/2, 40);

		}else{
			g.setColor(new Color(220,255,230));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.black);
			g.drawRect(0, 0, WIDTH-1, HEIGHT-1);
			if(roundMessage !=null && sequenceLength != null){

				g.drawString(roundMessage, (WIDTH - fm.stringWidth(roundMessage))/2, 20);
				g.drawString(sequenceLength, (WIDTH - fm.stringWidth(sequenceLength))/2, 40);
			}
		}
	}
}
