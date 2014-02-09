package org.adventure.commands;


public class ExamineCommand extends ItemCommand {

	
	

	public ExamineCommand() {
		super();
		this.addCommandPattern("examine <item>");
		this.addCommandPattern("look <item>");
	}

	@Override
	public void action(Command command) {
		super.action(command);
		System.out.println(getItem("<item>").getLongDescription());
	}


	
	
}
