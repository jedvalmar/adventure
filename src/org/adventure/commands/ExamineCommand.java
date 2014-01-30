package org.adventure.commands;


public class ExamineCommand extends ItemCommand {

	
	

	public ExamineCommand() {
		super();
		this.addVerb("examine");
		this.addVerb("look");
	}

	@Override
	public void action(Command command) {
		super.action(command);
		System.out.println(getCurrentItem().getLongDescription());
	}


	
	
}
