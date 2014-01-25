package org.adventure.commands;
import org.adventure.GameState;
import org.adventure.Room;


public class MoveCommand extends Command {
	private Room roomToMoveTo;
	
	public MoveCommand(Room roomToMoveTo) {
		super();
		this.roomToMoveTo = roomToMoveTo;
	}

	@Override
	public void action(Room room) {
		GameState.getState().gotoRoom(roomToMoveTo);
	}

	
	
}
