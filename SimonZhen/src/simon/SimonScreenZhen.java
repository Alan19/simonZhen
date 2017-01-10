package simon;

import java.awt.Color;
import java.util.ArrayList;
import guiPractice.components.Action;
import guiPractice.components.ClickableScreen;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class SimonScreenZhen extends ClickableScreen implements Runnable {
	
	private TextLabel label;
	private ButtonInterfaceZhen[] buttons;
	private ProgressInterfaceZhen progress;
	private ArrayList<MoveInterfaceZhen> moveList;
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;

	public SimonScreenZhen(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	@Override
	public void run() {
		label.setText("");
		nextRound();
	}
	
	public void changeText(String message){
		try {
			Thread.sleep(1000);
			label.setText(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber++;
		moveList.add(randomMove());
		progress.setRound(roundNumber);
		progress.setSequenceSize(moveList.size());
		
		changeText("Simon's Turn");
		label.setText("");
		playSequence();
		changeText("Your turn");
		label.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	private void playSequence() {
		ButtonInterfaceZhen b = null;
		for (int i = 0; i < moveList.size(); i++) {
			if(b != null) b.dim();
			b = moveList.get(i).getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			b.dim();
		}
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		int numberOfButtons = 6;
		Color[] colors = new Color[6];
		colors[0] = new Color(150,204,80);
		colors[1] = new Color(51,180,30);
		colors[2] = new Color(83,10,10);
		colors[3] = new Color(72,125,38);
		colors[4] = new Color(100,125,255);
		colors[5] = new Color(120,125,179);
		buttons = new ButtonInterfaceZhen[numberOfButtons];
		for (int i = 0; i < numberOfButtons; i++) {
			buttons[i] = getAButton();
			buttons[i].setColor(colors[i]);
			buttons[i].setX(i * 100 + 20);
			buttons[i].setY(200 - (int)(100*Math.sin(i)));
			final ButtonInterfaceZhen b = buttons[i];
			
			buttons[i].setAction(new Action() {
				
				@Override
				public void act() {
					if (acceptingInput) {
						Thread blink = new Thread(new Runnable() {
							public void run() {
								b.highlight();
								try {
									Thread.sleep(400);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
							}
						});
						blink.start();
						if(acceptingInput && b == moveList.get(sequenceIndex).getButton()){
							sequenceIndex++;
						}
						else if (acceptingInput) {
							gameOver();
							return;
						}
						if(sequenceIndex == moveList.size()){
							Thread nextRound = new Thread(SimonScreenZhen.this);
							nextRound.start();
						}
					}					
				}
			});
			viewObjects.add(buttons[i]);
		}
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		moveList = new ArrayList<MoveInterfaceZhen>();
		//add 2 moves to start
		lastSelectedButton = -1;
		moveList.add(randomMove());
		moveList.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	private void gameOver(){
		progress.gameOver();
	}
	
	private MoveInterfaceZhen randomMove() {
		int select = (int) (Math.random()*buttons.length);
		while(select == lastSelectedButton){
			select = (int) (Math.random()*buttons.length);
		}
		lastSelectedButton = select;
		return new Move(buttons[select]);
	}

	private ProgressInterfaceZhen getProgress() {
		return new Progress();
	}
	

	private ButtonInterfaceZhen getAButton() {
		return new Button();
	}

}
