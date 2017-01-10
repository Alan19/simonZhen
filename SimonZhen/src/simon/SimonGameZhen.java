package simon;

import guiPractice.GUIApplication;

@SuppressWarnings("serial")
public class SimonGameZhen extends GUIApplication {
	
	public SimonGameZhen(int width, int height) {
		super(width, height);
	}

	@Override
	protected void initScreen() {
		SimonScreenZhen sScreen = new SimonScreenZhen(getWidth(), getHeight());
		setScreen(sScreen);
	}

	public static void main(String[] args) {
		SimonGameZhen game = new SimonGameZhen(800, 500);
		Thread app = new Thread(game);
		app.start();
	}

}
