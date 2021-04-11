
package lmh.actions;

import lmh.items.*;
import lmh.interfaces.*;
import lmh.creatures.*;

// A class that represents equipping an item action.
public class EquipItemAction extends UseItemAction
{
  public EquipItemAction()
  {
    super ("Change weapon/armor", new String[]{
           "These are the weapons/armor you can equip: ",
           "Please pick a wepon/armor you would like to equip: ",
           "You don't have any weapons/armor in your inventory that you can equip!" });
  }

  // Drives the action. Calls the method of the parent class by passing its own
  // values.
  public void execute (Fighter attacker)
  {
    Hero hero = (Hero) attacker;
    useItemFromList (hero.getInventory().getItems(), Equipment.class, hero.getLevel(), hero);
  }

  // Checks if an item can be equpped by a hero.
  // An item can be equipped if it is equipment and not above a hero level.
  protected boolean canBeUsed (Item item, int condition)
  {
    return ((Equipment) item).canBeEquipped (condition);
  }

  // Equips an item on a hero.
  protected void useItem (Hero hero, Item item)
  {
    hero.useItem ((Equipment) item);
  }
}
