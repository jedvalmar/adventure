package org.adventure.commands;
import java.util.ArrayList;
import java.util.List;

import org.adventure.Room;


public abstract class Command {

		private List<String> validValues = new ArrayList<String>();
		
		public abstract void action(Room room);

		public void addValidValue(String... validValue) {
			for (String string : validValue) {
				this.validValues.add(string.toLowerCase());				
			}
		}
		
		public boolean contains(String value) {
			return this.validValues.contains(value.toLowerCase());
		}
}
