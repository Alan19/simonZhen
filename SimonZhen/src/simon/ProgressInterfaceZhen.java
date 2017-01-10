package simon;

import guiPractice.components.Visible;

public interface ProgressInterfaceZhen extends Visible {
	void setRound(int round);
	void setSequenceSize(int sequence);
	void gameOver();
}
