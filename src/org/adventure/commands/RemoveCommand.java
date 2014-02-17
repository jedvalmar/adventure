package org.adventure.commands;

import org.adventure.PlayerCharacter;
import org.adventure.items.IItem;
import org.adventure.items.IWearable;
import org.adventure.items.armor.Armor;

public class RemoveCommand extends ItemCommand {
	public RemoveCommand() {
		super();
		this.addCommandPattern("remove <item>");
	}
	
	@Override
	public void action(Command command, PlayerCharacter character) {
		super.action(command, character);
		IItem item = getItem("<item>", character);
		if (item != null) {
			if (character.getFreeHands() > 0) {
				if (character.isWearing(item)) {
					if (item instanceof IWearable) {
						IWearable wearable = (IWearable) item;
						character.unWear(wearable);
					}
					else if (item instanceof Armor) {
						Armor armor = (Armor) item;
						character.unWear(armor);
					}
					character.sendMessage(new StringBuilder("You remove ").append(command.getItem("<item>")).toString());
				}
				else {
					character.sendMessage(new StringBuilder("You are not wearing the ").append(command.getItem("<item>")).toString());
				}
			}
			else {
				character.sendMessage("Your hands are full.");
			}
		}
	}
}
