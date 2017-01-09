package simon;

import java.awt.Color;

import guiPractice.components.Action;
import guiPractice.components.Clickable;

public interface ButtonInterfaceZhen extends Clickable{
	public void setAction(Action action);

	public void highlight();

	public void dim();

	public void setX(int i);

	public void setColor(Color color);

	public void setY(int i);
}
