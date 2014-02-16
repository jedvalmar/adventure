package org.adventure.commands.navigation;
import org.adventure.PlayerCharacter;
import org.adventure.Room;
import org.adventure.commands.Action;
import org.adventure.commands.Command;

public class MoveCommand extends Action {
	private Room roomToMoveTo;
	
	public MoveCommand(Room roomToMoveTo, Direction direction) {
		super();
		this.roomToMoveTo = roomToMoveTo;
		this.addCommandPattern(direction.getValidValues());
	}

	public MoveCommand(Room roomToMoveTo, String... validValues) {
		super();
		this.roomToMoveTo = roomToMoveTo;
		this.addCommandPattern(validValues);
	}
	
	@Override
	public void action(Command command, PlayerCharacter character) {
		character.gotoRoom(roomToMoveTo);
	}

	
	
}
