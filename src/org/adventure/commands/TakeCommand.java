package org.adventure.commands;

import org.adventure.IContainer;
import org.adventure.PlayerCharacter;
import org.adventure.items.IItem;

public class TakeCommand extends ItemCommand {
	public TakeCommand() {
		super();
		this.addCommandPattern("((take)|(get)) <item> from <container>", 
							   "((take)|(get)) <item>");
	}

	@Override
	public void action(Command command, PlayerCharacter character) {
		super.action(command, character);
		IItem item = null;
		if (command.hasItem("<container>")) {
			IContainer container = (IContainer)getItem("<container>", character);
			if (container != null) {
				item = container.getItem(command.getItem("<item>"));	
				character.sendMessage(new StringBuilder("You take the ").append(command.getItem("<item>"))
						.append(" from the ").append(command.getItem("<container>")).toString());
			}
		}
		else {
			item = getItem("<item>",character);
		}
		if (item != null) {
			character.sendMessage(new StringBuilder("You pick up the ").append(command.getItem("<item>")).toString());
			item.setContainer(character);			
		}
	}

}
