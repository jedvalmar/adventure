package org.adventure.commands;


public class EndCommand extends Action {

	@Override
	public void action(Command command) {
		getState().setEnd(true);
	}

}
