package test.org.adventure.commands;

import org.adventure.CharacterDAO;
import org.adventure.CharacterHolder;
import org.adventure.PlayerCharacter;
import org.adventure.Room;
import org.adventure.commands.CommandHandler;
import org.adventure.items.armor.ArmorFactory;
import org.adventure.items.weapons.Weapon;
import org.adventure.items.weapons.WeaponFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BattleConfiguration.class, CommandHandler.class, CharacterHolder.class, CharacterDAO.class})
public class BattleTest {

	@Autowired
	Room farmhouse;
	
	@Autowired
	CommandHandler commandHandler;
	
	@Autowired
	CharacterHolder characterHolder;
	
	@Autowired
	CharacterDAO characterDAO;
	
	@Test
	public void test() {
		characterHolder.setCharacterId("2");
		PlayerCharacter pc = characterDAO.load("1");
		pc.wear(new ArmorFactory().getLeather());
		pc.gotoRoom(farmhouse);
		Weapon sword = new WeaponFactory().newShortSword();
		sword.setBaseAttackRate(0);
		characterHolder.getCharacter().setRightHand(sword);
		characterHolder.getCharacter().gotoRoom(farmhouse);
		int i=100;
		while(i-- > 0) {
			commandHandler.handle("attack the left leg of rain");			
		}
	}
}
