package org.adventure.commands.combat;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.adventure.BodyPart;
import org.adventure.PlayerCharacter;
import org.adventure.commands.Command;
import org.adventure.items.IItem;
import org.adventure.items.WearableType;
import org.adventure.items.weapons.Weapon;
import org.adventure.random.SkillType;

public class AttackCommand extends CharacterCommand {
	public AttackCommand() {
		super();
		this.addCommandPattern("attack <character>");
	}

	@Override
	public void action(Command command, PlayerCharacter character) {
		super.action(command, character);
		// Is the item in your hand.
		PlayerCharacter defender = getCharacter("<character>", character);
		if (character != null) {
			Weapon weapon = character.getWeapon();
			boolean hit = character.skillCheck(weapon.getWeaponType(), getDefLevel(defender));
			if (hit) {
				StringBuilder sb = new StringBuilder();
				sb.append(character.getName()).append(" hits ")
				.append(defender.getName()).append(" with ").append(weapon.getName());
				List<BodyPart> bodyParts = character.getBodyParts();
				BodyPart bodyPart = bodyParts.get(ThreadLocalRandom.current().nextInt(bodyParts.size() - 1));
				int damage = defender.calculateDamage(weapon.getDamages(), bodyPart);
				character.broadcastMessage(sb.append("and takes ").append(damage).append(" points of damage.  To his ").append(bodyPart.getName()).toString());
			}
			else {
				character.broadcastMessage(new StringBuilder().append(character.getName()).append(" misses ")
						.append(defender.getName()).append(" with ").append(weapon.getName()).toString());
			}
			character.setBusyFor(weapon.getBaseAttackRate());
		}
	}
	

	
	private int getDefLevel(PlayerCharacter character) {
		return character.getSkill(SkillType.PARRY_MELEE).getValue();
	}
	
	private void calculateDamage(PlayerCharacter defender) {
		
	}
}
