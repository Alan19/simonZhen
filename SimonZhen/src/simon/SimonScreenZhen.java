package simon;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import guiPractice.components.Action;
import guiPractice.components.Button;
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
		progress.setRound(moveList.size());
		progress.setSequenceSize(moveList.size());
		changeText("Simon's Turn");
		label.setText("");
		playSequence();
		changeText("Your turn");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	private void playSequence() {
		ButtonInterfaceZhen b = null;
		for (int i = 0; i < buttons.length; i++) {
			if(b != null) b.dim();
			b = moveList.get(sequenceIndex).getButton();
			int sleepTime = 2000 - 10*(5-i);
			if(i <= 200) sleepTime = 200;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			b.dim();
		}
	}

	public void initAllObjects(List<Visible> viewObjects) {
		addButtons(viewObjects);
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

	private MoveInterfaceZhen randomMove() {
		ButtonInterfaceZhen b;
		int selectedButton = 0;
		while (true) {
			selectedButton = (int) (Math.random()*moveList.size());
			if(selectedButton != lastSelectedButton) break;
		}
		b = buttons[selectedButton];
		return getMove(b);
	}

	private MoveInterfaceZhen getMove(ButtonInterfaceZhen b) {
		// TODO Auto-generated method stub
		return null;
	}

	private ProgressInterfaceZhen getProgress() {
		// Placeholder until partner finishes implementation of ProgressInterface
		return null;
	}
	
	public void addButtons(List<Visible> viewObjects){
		int numberOfButtons = 6;
		Color[] colors = new Color[6];
		colors[0] = new Color(100,180,255);
		colors[1] = new Color(51,180,30);
		colors[2] = new Color(83,10,200);
		colors[3] = new Color(72,125,230);
		colors[4] = new Color(100,125,200);
		colors[5] = new Color(72,125,230);
		for (int i = 0; i < numberOfButtons; i++) {
			final ButtonInterfaceZhen b = getAButton();
			b.setColor(colors[i]);
			b.setX((i+1) * 30);
			b.setY(50);
			b.setAction(new Action() {
				
				@Override
				public void act() {
					if (acceptingInput) {
						Thread blink = new Thread(new Runnable() {
							public void run() {
								b.highlight();
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								b.dim();
							}
						});
						blink.start();
						if(b == moveList.get(sequenceIndex).getButton()){
							sequenceIndex++;
						}
						if(sequenceIndex == moveList.size()){
							Thread nextRound = new Thread(SimonScreenZhen.this);
							nextRound.start();
						}
					}					
				}
			});
			viewObjects.add(b);
		}
	}

	private ButtonInterfaceZhen getAButton() {
		// TODO Auto-generated method stub
		return null;
	}


}
