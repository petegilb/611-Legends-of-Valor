
package lmh.actions;

import java.util.*;
import lmh.interfaces.*;
import lmh.creatures.*;

// A class that represents an attack action.
// An attack action occurs when player selects "attack" or "cast spell" during
// combat and when a monster attacks a hero.
public class AttackAction extends CombatAction
{
  // Damage to be dealt during the attack
  private int damage;

  public AttackAction (Fighter defender, int damage)
  {
    super ("Attack!", null, defender);
    this.damage = damage;
  }

  public void setDamage (int damage)
  {
    this.damage = damage;
  }

  public int getDamage()
  {
    return damage;
  }

  // Drives the action.
  // Takes an attacker and a defender from parent class, calculates their
  // dodge chance, health, defense and displays the outcome.
  public void execute (Fighter attacker)
  {
    Creature attCreature = (Creature) attacker;
    Creature defCreature = (Creature) defender;
    Random rand = new Random();

    if (rand.nextInt(100) < defender.calculateDodgeChance())
      System.out.println (defCreature.getName() + " dodged " + attCreature.getName() + "'s attack!");
    else
    {
      int defense = defender.calculateDefense();

      if (damage > defense)
      {
        defCreature.setHealth (defCreature.getHealth() + defense - damage);
        System.out.println();
        System.out.print (defCreature.getName() + " takes " + damage +
                          " damage from " + attCreature.getName());
      }
      else
      {
        System.out.print (defCreature.getName() + "'s defense deflects all damage from " +
                          attCreature.getName() + "!");
      }
    }
  }
}
