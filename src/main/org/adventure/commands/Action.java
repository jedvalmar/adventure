package org.adventure.commands;
import java.util.ArrayList;
import java.util.List;

import org.adventure.GameState;


public abstract class Action {

		private List<String> commandPatters = new ArrayList<String>();
		
		public abstract void action(Command command);

		public Action addCommandPattern(String... commandPattern) {
			for (String string : commandPattern) {
				this.commandPatters.add(string.toLowerCase());				
			}
			return this;
		}
		
		
		public boolean matches(Command command) {
			for (String commandPattern : this.commandPatters) {
				if (command.matches(commandPattern))
					return true;
			}
			return false;
		}
		public GameState getState() {
			return GameState.getState();
		}
}
