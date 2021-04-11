
package lmh.actions;

import lmh.interfaces.*;
import lmh.items.*;
import lmh.creatures.*;

// A class that represents selling an item action.
// Selling an item action occurs when the player encounters a market and chooses
// to vist it.
public class SellItemAction extends UseItemAction
{
  public SellItemAction()
  {
    super ("Sell item", new String[]{
           "These are the items you can sell: ",
           "Please pick an item to sell: ",
           "You don't have any items you can sell!" });
  }

  // Drives the action. Uses a method from the parent class by passing its own values.
  public void execute (Fighter attacker)
  {
    Hero hero = (Hero) attacker;
    useItemFromList (hero.getInventory().getItems(), Item.class, hero.getLevel(), hero);
  }

  // Checks is an item can be sold.
  protected boolean canBeUsed (Item item, int condition)
  {
    return ((Tradeable) item).canBeSold();
  }

  // Sells the item by passing control to the hero that's selling it.
  protected void useItem (Hero hero, Item item)
  {
    hero.sellItem (item);
  }
}
