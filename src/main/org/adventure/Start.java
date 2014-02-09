package org.adventure;
import java.io.IOException;

import org.adventure.commands.CommandCondition;
import org.adventure.commands.CommandHandler;
import org.adventure.commands.navigation.Direction;
import org.adventure.commands.navigation.MoveCommand;
import org.adventure.items.Container;
import org.adventure.items.Item;
import org.adventure.items.Wearable;
import org.adventure.items.WearableContainer;
import org.adventure.items.WearableType;


public class Start {

	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		GameState state = GameState.getState();
		
		Room room1 = new Room().setDescription("You are in a field of wheat." + 
		"To the North is a Farm House in the distance, to the South a lake, to the East are mountains.");
		
		Room room2 = new Room().setDescription("You aproach a small farm house with a thatched roof...");
		
		//TODO:  This is going to be common code (bi-directional move command should be created to make this easier.)
		room1.addCommand(new MoveCommand(room2, Direction.NORTH));
		room2.addCommand(new MoveCommand(room1, Direction.SOUTH));
		final Item gloves = new Wearable(WearableType.GLOVES)
			.setName("gloves")
			.setDescription("A pair of thick leather gloves.")
			.setLongDescription("Workmans gloves.")
			.setVolume(1)
			.setWeight(1);
		
		final Item coat = new WearableContainer(WearableType.JACKET, 5)
		.setContentsVisible(true)
		.setName("coat")
		.setDescription("A warn cotton coat.")
		.setLongDescription("A ragged coat with an inner pocket.")
		.setVolume(1)
		.setWeight(1);
	
		final Item jacket = new WearableContainer(WearableType.JACKET, 5)
		.setName("jacket")
		.setDescription("A warn cotton jacket.")
		.setLongDescription("A ragged coat with an inner pocket.")
		.setVolume(1)
		.setWeight(1);
		
		Container pot = new Container("pot", "A pot of boiling water.", 1, 1, "As you look closly, a knife seems to have fallen in the pot");
		pot.addCommandCondition(GameState.getState().takeCommand, new CommandCondition() {
			@Override
			public boolean conditional(GameState state) {
				if (state.getCharacter().getWorn(WearableType.GLOVES) != null) {
					return true;
				}
				System.out.println("Ouch thats hot!");
				return false;
			}
		});
		pot.setContentsVisible(true);
		pot.setContentsPrefix("In the pot is ");
		pot.setVolumeCapacity(5);
		pot.setVolume(1);
		pot.addItem(new Item().setName("Knife").setDescription("A small knife."));
		Room farmHouse = new Room();
		farmHouse.setDescription("You are in a quient farm house.");
		farmHouse.addItems(gloves, pot, coat, jacket);
		
		//TODO:  Here is our bi-directional movement again.
		farmHouse.addCommand(new MoveCommand(room2, Direction.DOOR));
		room2.addCommand(new MoveCommand(farmHouse, Direction.DOOR));
		
		state.setCurrentRoom(farmHouse) ;
		CommandHandler commandHandler = new CommandHandler();
		while (state.isEnd() == false) {
			commandHandler.promptUser();			
		}
		
		System.out.println("Thank you, please come again.");
	}

}
