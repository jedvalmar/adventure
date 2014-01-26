package org.adventure.commands.navigation;
import org.adventure.Room;
import org.adventure.commands.Command;


public class MoveCommand extends Command {
	private Room roomToMoveTo;
	
	public MoveCommand(Room roomToMoveTo, Direction direction) {
		super();
		this.roomToMoveTo = roomToMoveTo;
		this.addValidValue(direction.getValidValues());
	}

	public MoveCommand(Room roomToMoveTo, String... validValues) {
		super();
		this.roomToMoveTo = roomToMoveTo;
		this.addValidValue(validValues);
	}
	
	@Override
	public void action() {
		getState().gotoRoom(roomToMoveTo);
	}

	
	
}
