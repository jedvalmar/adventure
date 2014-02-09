package org.adventure.commands;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.adventure.GameState;



public class CommandHandler {
	GameState state = GameState.getState();

	public void processAction(Action action, Command command) {
		action.action(command);
	}
	
	public CommandHandler() {
		super();
		List<Action> validCommands = state.getCurrentRoom().getValidCommands();
		EndCommand endCommand = new EndCommand();
		endCommand.addCommandPattern("Bye");
		endCommand.addCommandPattern("Good Bye");
		validCommands.add(endCommand);
		ExamineCommand examineCommand = new ExamineCommand();
		validCommands.add(examineCommand);
		validCommands.add(GameState.getState().takeCommand);
		MeCommand meCommand = new MeCommand();
		meCommand.addCommandPattern("me");
		validCommands.add(meCommand);
		
		validCommands.add(new DropCommand());
		validCommands.add(new WearCommand());
	}
	
	public void promptUser() throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      System.out.println("Room:");
		  System.out.println(state.getCurrentRoom().getPrompt());
		  System.out.println("Command:");
	      String cmd = br.readLine();
	     
	      Command command = constructCommand(cmd);
	      Action action = getCommand(command);
	      if (action != null) {
	    	  processAction(action, command);
	      }
	      else {
	    	  System.out.println("Who's piloting this ship?");
	      }
	}
	
	public Action getCommand(Command command) {
		List<Action> validCommands = state.getCurrentRoom().getValidCommands();
		for (Action action : validCommands) {
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
