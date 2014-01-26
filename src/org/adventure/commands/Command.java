package org.adventure.commands;
import java.util.ArrayList;
import java.util.List;

import org.adventure.GameState;


public abstract class Command {

		private List<String> validValues = new ArrayList<String>();
		
		public abstract void action();

		public Command addValidValue(String... validValue) {
			for (String string : validValue) {
				this.validValues.add(string.toLowerCase());				
			}
			return this;
		}
		
		public boolean contains(String value) {
			return this.validValues.contains(value.toLowerCase());
		}
		public GameState getState() {
			return GameState.getState();
		}
}
