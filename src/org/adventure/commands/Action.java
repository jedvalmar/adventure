package org.adventure.commands;
import java.util.ArrayList;
import java.util.List;

import org.adventure.GameState;


public abstract class Action {

		private List<String> verbs = new ArrayList<String>();
		
		public abstract void action(Command command);

		public Action addVerb(String... validValue) {
			for (String string : validValue) {
				this.verbs.add(string.toLowerCase());				
			}
			return this;
		}
		
		
		public boolean contains(String value) {
			return this.verbs.contains(value.toLowerCase());
		}
		public GameState getState() {
			return GameState.getState();
		}
}
