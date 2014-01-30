package org.adventure.commands;


public class TakeCommand extends ItemCommand {
	public TakeCommand() {
		super();
		this.addVerb("take");
	}

	@Override
	public void action(Command command) {
		super.action(command);
		boolean added = getState().getCharacter().addItem(getCurrentItem());
		if (added) {
			getState().getCurrentRoom().removeItem(getCurrentItem());
		}
	}

}
