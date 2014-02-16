package org.adventure.commands.combat;

import org.adventure.PlayerCharacter;
import org.adventure.commands.Action;
import org.adventure.commands.Command;

public class CharacterCommand extends Action {

	Command command;
	@Override
	public void action(Command command, PlayerCharacter character) {
		this.command = command;
	}

	public org.adventure.PlayerCharacter getCharacter(String characterToken, PlayerCharacter character) {
		String characterName = this.command.getItem(characterToken);
		org.adventure.PlayerCharacter target = character.getCurrentRoom().getCharacter(characterName);
		if (target == null) {
			character.sendMessage(new StringBuilder("Could not find ").append(characterName).toString());
		}
		
		return target;
	}
}
