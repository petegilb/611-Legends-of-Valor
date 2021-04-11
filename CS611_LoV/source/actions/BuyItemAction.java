
package lmh.actions;

import java.util.*;
import lmh.items.*;
import lmh.interfaces.*;
import lmh.creatures.*;
import lmh.locations.*;

// Class for the action of buying an item.
// Buying an item occurs at a market when a player choose the appropriate option.
public class BuyItemAction extends UseItemAction
{
  // Location where buying takes place.
  // Doesn't have to be a market, could be an NPC in the future versions of the game
  // or another player.
  private Location location;

  public BuyItemAction (Location location)
  {
    super ("Buy item", new String[]{
           "These are the items you can buy: ",
           "Please pick an item to buy: ",
           "You can't afford any items at this " + location + "!" });

    this.location = location;
  }

  public void setLocation (Location location)
  {
    this.location = location;
  }

  public Location getLocation()
  {
    return location;
  }

  // Drive the action. Calls the method from the parent class with its values.
  public void execute (Fighter attacker)
  {
    Hero hero = (Hero) attacker;
    useItemFromList (location.getItems(), Item.class, hero.getGold(), hero);
  }

  // Checks if item can be bought with hero's gold.
  protected boolean canBeUsed (Item item, int condition)
  {
    return ((Tradeable) item).canBeBought (condition);
  }

  // If item is being bought, passes the control to the hero that's buying it.
  protected void useItem (Hero hero, Item item)
  {
    hero.buyItem (item);
  }
}
