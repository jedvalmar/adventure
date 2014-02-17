package test.org.adventure.commands;

import org.adventure.Room;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BattleConfiguration {

	@Bean
	public Room farmhouse() {
		Room r = new Room();
		return r;
	}

}
