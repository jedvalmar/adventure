package org.adventure.commands;

import org.adventure.PlayerCharacter;


public class SayCommand extends Action {

	public SayCommand() {
		super();
		this.addCommandPattern("say <message>");
	}
	
	@Override
	public void action(Command command, PlayerCharacter character) {
		String message = command.getItem("<message>");
		character.getCurrentRoom().sendMessage(character.getName() + " says '" + message + "'");
	}

}
