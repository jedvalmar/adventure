package org.adventure.commands;
import org.adventure.Room;


public class ExamineCommand extends ItemCommand {

	
	@Override
	public String getVerb() {
		return "examine";
	}

	@Override
	public void action(Room room) {
		System.out.println(getCurrentItem().getLongDescription());
	}


	
	
}
