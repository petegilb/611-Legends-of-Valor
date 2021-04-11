
package lmh.creatures;

import lmh.interfaces.*;
import lmh.items.*;
import lmh.interfaces.*;
import lmh.actions.*;

// A class for abstracting a monster creature in the game.
public abstract class Monster extends Creature implements Fighter
{
  protected int damage;
  protected int defense;
  protected int dodgeChance;

  public Monster()
  {
    this ("unknown monster", 1, "Monster", 0, 0, 0);
  }

  public Monster (String name, int level, String type,
                 int damage, int defense, int dodgeChance)
  {
    super (name, type, level);
    this.damage = damage;
    this.defense = defense;
    this.dodgeChance = dodgeChance;
  }

  public void setDamage (int damage)
  {
    this.damage = damage;
  }

  public int getDamage()
  {
    return damage;
  }

  public void setDefense (int defense)
  {
    this.defense = defense;
  }

  public int getDefense()
  {
    return defense;
  }

  public void setDodgeChance (int dodgeChance)
  {
    this.dodgeChance = dodgeChance;
  }

  public int getDodgeChance()
  {
    return dodgeChance;
  }

  public String toString()
  {
    if (isFainted())
      return (name + " has fainted!");

    return super.toString() + ", Damage: " + damage + ", Defense: " + defense +
           ", Dodge Chance: " + dodgeChance;
  }

  public int calculateDamage()
  {
    return damage;
  }

  public int calculateDefense()
  {
    return defense;
  }

  public int calculateDodgeChance()
  {
    return (int) (dodgeChance * 0.1);
  }

  public void nextCombatRound (Fighter defender)
  {
    (new AttackAction (defender, calculateDamage())).execute (this);
  }

  public void roundIncrease()
  {
    return;
  }

  public void combatIncrease (int opponentLevel, int health)
  {
    return;
  }

  public void levelIncrease()
  {
    return;
  }

  public void combatDecrease (int health)
  {
    return;
  }

  // Process the skill decrease after being affected by a spell cast by a hero.
  public void skillDecrease (Usable item)
  {
    if (item instanceof Spell)
    {
      Spell spell = (Spell) item;
      int[] coefs = spell.getCoefs();

      damage = damage * (100 - coefs[0]) / 100;
      defense = defense * (100 - coefs[1]) / 100;
      dodgeChance = dodgeChance * (100 - coefs[2]) / 100;

      System.out.println (name + "'s skills decrease as a result of a spell!");
    }
  }
}
