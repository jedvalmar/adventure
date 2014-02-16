package org.adventure.commands;

import org.adventure.PlayerCharacter;

public class MeCommand extends Action {

	public MeCommand() {
		super();
		this.addCommandPattern("me");
	}

	@Override
	public void action(Command command, PlayerCharacter character) {
		String me = character.describeMe();
		character.sendMessage(me);
	}

}
