package org.adventure.commands;

import org.adventure.PlayerCharacter;
import org.adventure.items.IItem;
import org.springframework.stereotype.Component;

@Component
public class DropCommand extends ItemCommand {

	
	public DropCommand() {
		super();
		this.addCommandPattern("drop <item>");
	}



	@Override
	public void action(Command command, PlayerCharacter character) {
		super.action(command, character);
		// Is the item in your hand.
		IItem item = getItem("<item>", character);
		if (character.isHolding(item)) {
			item.setContainer(character.getCurrentRoom());			
		}		
		else {
			character.sendMessage("Drop the what?");
		}
	}

}
