package org.adventure.commands;

import org.adventure.IContainer;
import org.adventure.items.IItem;


public class TakeCommand extends ItemCommand {
	public TakeCommand() {
		super();
		this.addCommandPattern("take <item> from <container>", "take <item>");
	}

	@Override
	public void action(Command command) {
		super.action(command);
		IItem item = null;
		if (command.getItemMap().containsKey("<container>")) {
			IContainer container = (IContainer)getItem("<container>");
			if (container != null) {
				item = container.getItem(command.getItemMap().get("<item>"));				
			}
		}
		else {
			item = getItem("<item>");
		}
		boolean added = getState().getCharacter().addItem(item);
		if (added) {
			getState().getCurrentRoom().removeItem(item);
		}
	}

}
