package org.adventure.commands;

import org.adventure.PlayerCharacter;
import org.adventure.items.IItem;

public class ExamineCommand extends ItemCommand {

	public ExamineCommand() {
		super();
		this.addCommandPattern("((look)|(examine)) <item>");
		this.addCommandPattern("((look)|(examine))");
	}

	@Override
	public void action(Command command, PlayerCharacter character) {
		super.action(command, character);
		if (command.hasItem("<item>")) {
			IItem item = getItem("<item>", character);
			if (item != null) {
				character.sendMessage(item.getLongDescription());					
			}
		}
		else {
			character.sendRoom();
			
		}
	}


	
	
}
