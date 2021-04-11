
package lmh.actions;

import lmh.creatures.*;
import lmh.items.*;
import lmh.interfaces.*;

// Class that represents casting a spell action.
// Casting a spell action occurs during combat when a player select the
// appropriate option.
public class CastSpellAction extends CombatAction
{
  public CastSpellAction (Fighter defender) //Hero hero) //, )
  {
    super ("Cast spell", new String[]{
           "These are the spells you can cast: ",
           "Please pick a spell to cast: ",
           "You don't have any spells you can cast!" }, defender);
  }

  // Drives the aciton. Calls parent class method with its own values.
  public void execute (Fighter attacker)
  {
    Hero hero = (Hero) attacker;
    useItemFromList (hero.getInventory().getItems(), Spell.class, hero.getLevel(), hero);
  }

  // If a spell is being cast, passes control to the hero that's casting it with
  // an additon of a defender to cast it on.
  protected void useItem (Hero hero, Item item)
  {
    hero.useItem ((Usable) item, defender);
  }
}
