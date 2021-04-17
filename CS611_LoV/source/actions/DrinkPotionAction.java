
package lmh.actions;

import lmh.creatures.*;
import lmh.items.*;
import lmh.interfaces.*;

// Class for drinking a potion action. Drinking a potion can occur during combat
// and in the inventory when player chooses an appropriate option.
public class DrinkPotionAction extends UseItemAction
{
  public DrinkPotionAction()
  {
    super ("Drink potion", new String[]{
           "These are the potions you can drink: ",
           "Please pick a potion to drink: ",
           "You don't have any potions you can drink!" });
  }

  // Drives the action. Uses a method from the parent class by passing its own
  // values.
  public void execute (Fighter attacker)
  {
    Hero hero = (Hero) attacker;
    useItemFromList (hero.getInventory().getItems(), Potion.class, hero.getLevel(), hero);
  }
}
