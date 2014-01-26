package org.adventure.commands;


public class MeCommand extends Command {


	@Override
	public void action() {
		getState().getCharacter().describeMe();

	}

}
