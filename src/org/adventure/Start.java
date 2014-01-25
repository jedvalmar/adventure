package org.adventure;
import java.io.IOException;

import org.adventure.commands.CommandCondition;
import org.adventure.commands.MoveCommand;


public class Start {

	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		GameState state = GameState.getState();
		
		Room room1 = new Room();
		room1.setDescription("You are in a field of wheat." + 
		"To the North is a Farm House in the distance, to the South a lake, to the East are mountains.");
		
		Room room2 = new Room();
		room2.setDescription("You aproach a small farm house with a thatched roof...");
		
		MoveCommand move1_2 = new MoveCommand(room2);
		move1_2.addValidValue("N","North", "Go North");
		room1.addCommand(move1_2);
		
		MoveCommand move2_1 = new MoveCommand(room1);
		move2_1.addValidValue("S","South","Go South");
		room2.addCommand(move2_1);
		
		final Item gloves = new Item("gloves","A pair of thick leather gloves.", 1,1,"Workmans gloves.");
		Container pot = new Container("pot", "Pot of boiling water.", 1, 1, "As you look closly, a knife seems to have fallen in the pot");
		pot.addCommandCondition(GameState.getState().takeCommand, new CommandCondition() {
			@Override
			public boolean conditional() {
				if (GameState.getState().getCharacter().isHolding(gloves)) {
					return true;
				}
				System.out.println("Ouch thats hot!");
				return false;
			}
		});
		
		Room farmHouse = new Room();
		farmHouse.setDescription("You are in a quient farm house.");
		farmHouse.addItem(gloves,
				pot);
		MoveCommand leaveFarmhouse = new MoveCommand(room2);
		leaveFarmhouse.addValidValue("go door");
		farmHouse.addCommand(leaveFarmhouse);
		
		MoveCommand move2_farmhouse = new MoveCommand(farmHouse);
		move2_farmhouse.addValidValue("Open Door","go Door");
		room2.addCommand(move2_farmhouse);
		
		state.setCurrentRoom(farmHouse) ;
		while (state.isEnd() == false) {
			state.getCurrentRoom().getCommand();			
		}
		
		System.out.println("Thank you, please come again.");
	}

}
