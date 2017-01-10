package simon;

public class Move implements MoveInterfaceZhen{
	
	private ButtonInterfaceZhen button;

	public Move(ButtonInterfaceZhen b) {
		this.button = b;
	}

	@Override
	public ButtonInterfaceZhen getButton() {
		return button;
	}

}
