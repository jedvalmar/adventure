package org.adventure.commands;


public class EndCommand extends Command {

	@Override
	public void action() {
		getState().setEnd(true);
	}

}
