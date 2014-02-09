package org.adventure.commands;

import org.adventure.IContainer;
import org.adventure.items.IItem;

public class PutCommand extends ItemCommand {

	
	public PutCommand() {
		super();
		this.addCommandPattern("put <item> in <container>");
	}

	@Override
	public void action(Command command) {
		super.action(command);
		// Is the item in your hand.
		IItem item = getItem("<item>");
		if (command.getItemMap().containsKey("<container>")) {
			IContainer container = (IContainer)getItem("<container>");
			if (container != null) {
				if (container.addItem(item)) {
					System.out.println("you put it in the container.");
				}
			}
		}
		boolean added = getState().getCharacter().addItem(item);
		if (added) {
			getState().getCurrentRoom().removeItem(item);
		}

	}

}
