package org.adventure.commands;
import java.util.ArrayList;
import java.util.List;

import org.adventure.CharacterHolder;
import org.adventure.commands.combat.AttackCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommandHandler {
	
	@Autowired
	public CharacterHolder characterHolder;
	
	public TakeCommand takeCommand = new TakeCommand();
	
	public DropCommand dropCommand = new DropCommand();
	
	ExamineCommand examineCommand= new ExamineCommand();
	
	MeCommand meCommand = new MeCommand();
	
	DropCommand dropCommand2 = new DropCommand();
	
	WearCommand wearCommand = new WearCommand();
	
	PutCommand putCommand = new PutCommand();
	
	RemoveCommand removeCommand = new RemoveCommand();
	
	SayCommand sayCommand = new SayCommand();
	
	private List<Action> validCommands;
	
	public void processAction(Action action, Command command) {
		if (characterHolder.getCharacter().isBusy() == false) {
			action.action(command, characterHolder.getCharacter());			
		}
		else {
			characterHolder.getCharacter().sendMessage(new StringBuilder("Wait ").append(characterHolder.getCharacter().getBusyFor()).append(" seconds.").toString());
		}
	}
	
	public CommandHandler() {
		super();
		
	}

	public void handle(String cmd) {
		Command command = constructCommand(cmd);
	      Action action = getCommand(command);
	      if (action != null) {
	    	  processAction(action, command);
	      }
	      else {
	    	  characterHolder.getCharacter().sendMessage("Huh? I don't know what you meant by " + cmd);
	      }
	}
	
	public Action getCommand(Command command) {
		validCommands = new ArrayList<Action>();
		validCommands.add(examineCommand);
		validCommands.add(takeCommand);
		validCommands.add(meCommand);
		validCommands.add(dropCommand);
		validCommands.add(wearCommand);
		validCommands.add(putCommand);
		validCommands.add(removeCommand);
		validCommands.add(sayCommand);
		validCommands.add(new AttackCommand());
		List<Action> currentCommands = new ArrayList<Action>(characterHolder.getCharacter().getCurrentRoom().getValidCommands());
		currentCommands.addAll(this.validCommands);
		for (Action action : currentCommands) {
			if (action.matches(command)) {
				return action;
			}
		}
		return null;
	}
	
	private Command constructCommand(String cmd) {
		 Command command = new Command(cmd);
		 
	      
	     return command;
	}
}
