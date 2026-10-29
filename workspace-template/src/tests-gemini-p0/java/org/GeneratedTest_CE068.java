package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE068 {

@Test
public void testAttackAndIsAlive() {
    RPGCharacter hero = new RPGCharacter("Hero", 100, 20, 5);
    RPGCharacter monster = new RPGCharacter("Goblin", 30, 10, 2);

    // Damage = 20 - 2 = 18. Goblin hp: 30 - 18 = 12
    hero.attack(monster);
    assertEquals(12, monster.getHp());
    assertTrue(monster.isAlive());

    // Goblin hp: 12 - 18 = -6 <= 0
    hero.attack(monster);
    assertEquals(-6, monster.getHp());
    assertFalse(monster.isAlive());
}

@Test
public void testAttackMinimumOneDamage() {
    RPGCharacter attacker = new RPGCharacter("Weak", 100, 5, 0);
    RPGCharacter defender = new RPGCharacter("Armored", 100, 10, 20);

    // 5 - 20 = -15 -> max(damage, 1) = 1
    attacker.attack(defender);
    assertEquals(99, defender.getHp());
}

@Test
public void testHeal() {
    RPGCharacter hero = new RPGCharacter("Hero", 85, 20, 5);
    int hpAfterFirstHeal = hero.heal();
    assertEquals(95, hpAfterFirstHeal);

    // Cap at 100
    int hpAfterSecondHeal = hero.heal();
    assertEquals(100, hpAfterSecondHeal);
}

@Test
public void testGainExpAndLevelUp() {
    RPGCharacter hero = new RPGCharacter("Hero", 100, 20, 10);
    assertEquals(1, hero.getLevel());
    assertEquals(0, hero.getExp());

    // Needs 100 exp to level up from level 1 to level 2
    hero.gainExp(150);
    assertEquals(2, hero.getLevel());
    assertEquals(50, hero.getExp());
    assertEquals(120, hero.getHp());
    assertEquals(25, hero.getAttackPower());
    assertEquals(15, hero.getDefense());
}

}
