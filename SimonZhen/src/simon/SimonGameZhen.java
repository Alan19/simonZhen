package simon;

import guiPractice.GUIApplication;

@SuppressWarnings("serial")
public class SimonGameZhen extends GUIApplication {
	
	public SimonGameZhen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initScreen() {
		SimonScreenZhen sScreen = new SimonScreenZhen(getWidth(), getHeight());
		setScreen(sScreen);
	}

	public static void main(String[] args) {
		SimonGameZhen game = new SimonGameZhen();
		Thread app = new Thread(game);
		app.start();
	}

}
