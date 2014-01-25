package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.Room;

public class MeCommand extends Command {


	@Override
	public void action(Room room) {
		GameState.getState().getCharacter().describeMe();

	}

}
