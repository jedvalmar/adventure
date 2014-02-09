package org.adventure.commands;


public class MeCommand extends Action {


	@Override
	public void action(Command command) {
		getState().getCharacter().describeMe();

	}

}
