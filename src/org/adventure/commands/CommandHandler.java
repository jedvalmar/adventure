package org.adventure.commands;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.adventure.GameState;



public class CommandHandler {
	GameState state = GameState.getState();

	public void processAction(Command command) {
		command.action();
	}
	
	
	
	public CommandHandler() {
		super();
		List<Command> validCommands = state.getCurrentRoom().getValidCommands();
		EndCommand endCommand = new EndCommand();
		endCommand.addValidValue("Bye");
		endCommand.addValidValue("Good Bye");
		validCommands.add(endCommand);
		ExamineCommand examineCommand = new ExamineCommand();
		validCommands.add(examineCommand);
		validCommands.add(GameState.getState().takeCommand);
		MeCommand meCommand = new MeCommand();
		meCommand.addValidValue("me");
		validCommands.add(meCommand);
		
		validCommands.add(new DropCommand());
		
	}
	
	public void promptUser() throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      System.out.println("Room:");
		  System.out.println(state.getCurrentRoom().getPrompt());
		  System.out.println("Command:");
	      String cmd = br.readLine();
	      Command command = getCommand(cmd);
	      if (command != null) {
	    	  processAction(command);
	      }
	      else {
	    	  System.out.println("Who's piloting this ship?");
	      }
	}
	
	public Command getCommand(String cmdString) {
		List<Command> validCommands = state.getCurrentRoom().getValidCommands();
		for (Command command : validCommands) {
			if (command.contains(cmdString)) {
				return command;
			}
		}
		return null;
	}
}
