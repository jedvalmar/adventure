package org.adventure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterHolder {
	@Autowired
	CharacterDAO characterDAO;
	private static ThreadLocal<PlayerCharacter> character = new ThreadLocal<PlayerCharacter>();
	private CharacterHolder() {
		super();
	}

	public PlayerCharacter getCharacter() {
		return character.get();
	}
	
	public void setCharacterId(String cId) {
		character.set(characterDAO.load(cId));
	}
}
