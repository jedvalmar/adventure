package test.org.adventure.commands;

import org.adventure.CharacterDAO;
import org.adventure.CharacterHolder;
import org.adventure.PlayerCharacter;
import org.adventure.Room;
import org.adventure.commands.CommandHandler;
import org.adventure.items.armor.Armor;
import org.adventure.items.armor.ArmorFactory;
import org.adventure.items.weapons.Weapon;
import org.adventure.items.weapons.WeaponFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BattleConfiguration.class, CommandHandler.class, CharacterHolder.class, CharacterDAO.class})
public class BattleTest {
	Logger log = LoggerFactory.getLogger(BattleTest.class);
	@Autowired
	Room farmhouse;
	
	@Autowired
	CommandHandler commandHandler;
	
	@Autowired
	CharacterHolder characterHolder;
	
	@Autowired
	CharacterDAO characterDAO;
	
	@Test
	@DirtiesContext
	public void test() {
		Weapon sword = new WeaponFactory().newLongSword();
		Armor armor = new ArmorFactory().getLeather();
		float rounds= attack(sword, armor); 

	}
	
	@Test
	@DirtiesContext
	public void test2() {
		Weapon sword = new WeaponFactory().newShortSword();
		Armor armor = new ArmorFactory().getLeather();
		attack(sword, armor);

	}
	
	@Test
	@DirtiesContext
	public void test3() {
		Weapon sword = new WeaponFactory().newGreatSword();
		Armor armor = new ArmorFactory().getLeather();
		attack(sword, armor);

	}

	@Test
	@DirtiesContext
	public void test4() {
		Weapon sword = new WeaponFactory().newHandAxe();
		Armor armor = new ArmorFactory().getLeather();
		attack(sword, armor);
		
	}
	
	@Test
	@DirtiesContext
	public void test5() {
		Weapon sword = new WeaponFactory().newWarAxe();
		Armor armor = new ArmorFactory().getLeather();
		attack(sword, armor);
		
	}
	
	@Test
	@DirtiesContext
	public void test6() {
		Weapon sword = new WeaponFactory().newLongSword();
		Armor armor = new ArmorFactory().getIron();
		float rounds= attack(sword, armor); 

	}
	
	@Test
	@DirtiesContext
	public void test7() {
		Weapon sword = new WeaponFactory().newShortSword();
		Armor armor = new ArmorFactory().getIron();
		attack(sword, armor);

	}
	
	@Test
	@DirtiesContext
	public void test8() {
		Weapon sword = new WeaponFactory().newGreatSword();
		Armor armor = new ArmorFactory().getIron();
		attack(sword, armor);

	}

	@Test
	@DirtiesContext
	public void test9() {
		Weapon sword = new WeaponFactory().newHandAxe();
		Armor armor = new ArmorFactory().getIron();
		attack(sword, armor);
		
	}
	
	@Test
	@DirtiesContext
	public void test10() {
		Weapon sword = new WeaponFactory().newWarAxe();
		Armor armor = new ArmorFactory().getIron();
		attack(sword, armor);
		
	}
	
	@Test
	@DirtiesContext
	public void test11() {
		Weapon sword = new WeaponFactory().newLongSword();
		Armor armor = new ArmorFactory().getPaddedChain();
		float rounds= attack(sword, armor); 

	}
	
	@Test
	@DirtiesContext
	public void test12() {
		Weapon sword = new WeaponFactory().newShortSword();
		Armor armor = new ArmorFactory().getPaddedChain();
		attack(sword, armor);

	}
	
	@Test
	@DirtiesContext
	public void test13() {
		Weapon sword = new WeaponFactory().newGreatSword();
		Armor armor = new ArmorFactory().getPaddedChain();
		attack(sword, armor);

	}

	@Test
	@DirtiesContext
	public void test14() {
		Weapon sword = new WeaponFactory().newHandAxe();
		Armor armor = new ArmorFactory().getPaddedChain();
		attack(sword, armor);
		
	}
	
	@Test
	@DirtiesContext
	public void test15() {
		Weapon sword = new WeaponFactory().newWarAxe();
		Armor armor = new ArmorFactory().getPaddedChain();
		attack(sword, armor);
		
	}
	
	@Test
	@DirtiesContext
	public void test16() {
		Weapon sword = new WeaponFactory().newMaceLight();
		Armor armor = new ArmorFactory().getLeather();
		attack(sword, armor);
		
	}
	
	@Test
	@DirtiesContext
	public void test17() {
		Weapon sword = new WeaponFactory().newMaceHeavy();
		Armor armor = new ArmorFactory().getLeather();
		float rounds= attack(sword, armor); 

	}
	
	@Test
	@DirtiesContext
	public void test18() {
		Weapon sword = new WeaponFactory().newMaceLight();
		Armor armor = new ArmorFactory().getPaddedChain();
		attack(sword, armor);

	}
	
	@Test
	@DirtiesContext
	public void test19() {
		Weapon sword = new WeaponFactory().newMaceHeavy();
		Armor armor = new ArmorFactory().getPaddedChain();
		attack(sword, armor);

	}

	@Test
	@DirtiesContext
	public void test20() {
		Weapon sword = new WeaponFactory().newMaceLight();
		Armor armor = new ArmorFactory().getIron();
		attack(sword, armor);
		
	}
	
	@Test
	@DirtiesContext
	public void test21() {
		Weapon sword = new WeaponFactory().newMaceHeavy();
		Armor armor = new ArmorFactory().getIron();
		attack(sword, armor);
		
	}

	private float attack(Weapon sword, Armor armor) {
		int battles = 0;
		int totalRounds = 0;
		while (battles++ < 100) {
			characterDAO.init();
			characterHolder.setCharacterId("2");
			PlayerCharacter pc = characterDAO.load("1");
			Room room = new Room();
			pc.wear(armor);
			pc.gotoRoom(room);
			sword.setBaseAttackRate(0);
			characterHolder.getCharacter().setRightHand(sword);
			characterHolder.getCharacter().gotoRoom(room);
			int i=1;
			while(i++ < 100 && pc.getHealth() > 0) {
				//commandHandler.handle("attack the left leg of rain");	
				commandHandler.handle("attack rain");
			}
			totalRounds = totalRounds + i;
		}
		System.out.println(sword.getName() + " vs. " + armor.getName() + " Average rounds:" + totalRounds / 100);
		return totalRounds / 100;
	}
}
