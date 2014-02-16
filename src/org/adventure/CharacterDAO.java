package org.adventure;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class CharacterDAO {
	Map<String, PlayerCharacter> characters = new HashMap<String, PlayerCharacter>();
	
	@Resource(name="farmhouse")
	Room room;
	
	public CharacterDAO() {
		super();
	}
	
	@PostConstruct
	public void init() {
		PlayerCharacter character = new PlayerCharacter();
		character.setName("Rainer");
		characters.put("1", character);
		
		character = new PlayerCharacter();
		character.setName("Jontan");
		characters.put("2", character);
	}



	public PlayerCharacter load(String id) {
		PlayerCharacter character = characters.get(id);
		if (character.getCurrentRoom() == null) {
			character.gotoRoom(room);
		}
		return character;
	}
}
