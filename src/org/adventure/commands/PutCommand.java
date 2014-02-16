package org.adventure.commands;

import org.adventure.IContainer;
import org.adventure.PlayerCharacter;
import org.adventure.items.IItem;

public class PutCommand extends ItemCommand {

	
	public PutCommand() {
		super();
		this.addCommandPattern("put <item> in <container>");
	}

	@Override
	public void action(Command command, PlayerCharacter character) {
		super.action(command, character);
		// Is the item in your hand.
		IItem item = getItem("<item>", character);
		IContainer container = (IContainer)getItem("<container>", character);
		if (container != null && item != null) {
			item.setContainer(container);
			character.sendMessage(new StringBuilder("You put the ").append(command.getItem("<item>"))
					.append(" in the ").append(command.getItem("<container>")).toString());
		} 

	}

}
