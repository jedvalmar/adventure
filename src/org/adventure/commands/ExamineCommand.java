package org.adventure.commands;


public class ExamineCommand extends ItemCommand {

	
	@Override
	public String getVerb() {
		return "examine";
	}

	@Override
	public void action() {
		System.out.println(getCurrentItem().getLongDescription());
	}


	
	
}
