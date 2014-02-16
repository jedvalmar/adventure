package org.adventure;

import org.adventure.commands.navigation.Direction;
import org.adventure.commands.navigation.MoveCommand;
import org.adventure.items.Container;
import org.adventure.items.Item;
import org.adventure.items.Wearable;
import org.adventure.items.WearableContainer;
import org.adventure.items.WearableType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {
	
	@Bean
	public Room room1() {
		Room room1 =   new Room().setDescription("You are in a field of wheat.", 
				"To the North is a Farm House in the distance, to the South a lake, to the East are mountains.");
		
		return room1;
	}
	
	@Bean
	public Room room2() {
		Room room2 = new Room().setDescription("You are in a field of wheat.",
				"To the North is a Farm House in the distance, to the South a lake, to the East are mountains.");

		room2.addCommand(new MoveCommand(room1(), Direction.SOUTH));
		room1().addCommand(new MoveCommand(room2,Direction.NORTH));
		
		return room2;
	}
	
	@Bean
	public Room farmhouse() {
		final Item gloves = new Wearable(WearableType.GLOVES)
		.setName("gloves")
		.setDescription("A pair of thick leather gloves.")
		.setLongDescription("Workmans gloves.")
		.setVolume(1)
		.setWeight(1);
	
	final WearableContainer coat = new WearableContainer(WearableType.JACKET, 5);
	coat.setName("coat")
	.setDescription("A warn cotton coat.")
	.setLongDescription("A ragged coat with an inner pocket.")
	.setVolume(1)
	.setWeight(1);
	coat.setContentsVisible(true);

	final Item jacket = new WearableContainer(WearableType.JACKET, 5)
	.setName("jacket")
	.setDescription("A warn cotton jacket.")
	.setLongDescription("A ragged coat with an inner pocket.")
	.setVolume(1)
	.setWeight(1);
	
	Container pot = new Container("pot", "A pot of boiling water.", 1, 1, "As you look closly, a knife seems to have fallen in the pot");
//	pot.addCommandCondition(state.takeCommand, new CommandCondition() {
//		@Override
//		public boolean conditional(GameState state) {
//			if (state.getCharacter().getWorn(WearableType.GLOVES) != null) {
//				return true;
//			}
//			System.out.println("Ouch thats hot!");
//			return false;
//		}
//	});
	pot.setContentsVisible(true);
	pot.setContentsPrefix("In the pot is ");
	pot.setVolumeCapacity(5);
	pot.setVolume(1);
	pot.addItem(new Item().setName("Knife").setDescription("A small knife."));
	Room farmHouse = new Room();
	farmHouse.setDescription("You are in a quient farm house.");
	farmHouse.addItems(gloves, pot, coat, jacket);
	
	//TODO:  Here is our bi-directional movement again.
	farmHouse.addCommand(new MoveCommand(room2(), Direction.DOOR));
	
	return farmHouse;
	}
}
