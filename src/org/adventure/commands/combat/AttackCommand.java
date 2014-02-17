package org.adventure.commands.combat;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.adventure.BodyPart;
import org.adventure.PlayerCharacter;
import org.adventure.commands.Command;
import org.adventure.items.weapons.Weapon;
import org.adventure.random.SkillType;

public class AttackCommand extends CharacterCommand {

	public AttackCommand() {
		super();
		this.addCommandPattern("attack the <bodypart> of <character>");
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
			String attackType = weapon.getAttackType();
			StringBuilder sb = new StringBuilder();
			sb.append(character.getName()).append(" ").append(attackType).append(" ")
			.append(defender.getName()).append(" with ").append(weapon.getName());
			if (hit) {
				BodyPart bodyPart = null;
				if (command.hasItem("<bodypart>")) {
					bodyPart = getBodyPart(command.getItem("<bodypart>"), defender);
				}
				else {
					List<BodyPart> bodyParts = character.getBodyParts();
					bodyPart = bodyParts.get(ThreadLocalRandom.current().nextInt(bodyParts.size() - 1));					
				}
				int damage = defender.calculateDamage(weapon.getDamages(attackType), bodyPart);
				character.broadcastMessage(sb.append("and takes ").append(damage).append(" points of damage.  To his ").append(bodyPart.getName()).toString());
			}
			else {
				character.broadcastMessage(sb.append(character.getName()).append(" and misses.").toString());
			}
			character.setBusyFor(weapon.getBaseAttackRate());
		}
	}
	

	
	private int getDefLevel(PlayerCharacter character) {
		return character.getSkill(SkillType.PARRY_MELEE).getValue();
	}
	
	public BodyPart getBodyPart(String bodyPartName, PlayerCharacter character) {
		for (BodyPart bodyPart : character.getBodyParts()) {
			if (bodyPart.getName().toLowerCase().equals(bodyPartName)) {
				return bodyPart;
			}
		}
		return null;

	}
	
}
