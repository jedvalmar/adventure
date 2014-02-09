package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.items.IItem;

public class RemoveCommand extends ItemCommand {
	public RemoveCommand() {
		super();
//		this.addVerb("wear");
	}
	
	@Override
	public void action(Command command) {
		super.action(command);
		IItem leftHandItem = getState().getCharacter().getLeftHand();
		IItem rightHandItem = getState().getCharacter().getRightHand();
//		
//		if (command.getSubjectItem().equals(rightHandItem)) {
//			GameState.getState().getCharacter().wear(command.getSubjectItem());
//			getState().getCharacter().setRightHand(null);
//			System.out.println("You put on the " + command.getSubjectItem().getName());
//		}
//		else if (command.getSubjectItem().equals(leftHandItem)) {
//			GameState.getState().getCharacter().wear(command.getSubjectItem());
//			getState().getCharacter().setLeftHand(null);
//			System.out.println("You put on the " + command.getSubjectItem().getName());
//		}
	}
}
