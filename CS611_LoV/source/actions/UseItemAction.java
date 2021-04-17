
package lmh.actions;

import java.util.*;
import lmh.*;
import lmh.creatures.*;
import lmh.items.*;
import lmh.interfaces.*;

// An abstraction that represents all actions that use an item (drink potion,
// cast spell, equip/buy/sell item).
public abstract class UseItemAction extends PickItemAction
{
  public UseItemAction (String name, String[] messages)
  {
    super (name, messages);
  }

  // Takes a collection and an item class, retrieves all the items from the
  // collection of the same class, then asks the player to pick one.
  // If 0 is picked, null is returned. Uses a method from the parent class to
  // pick an item from a list.
  protected <T1, T2> void useItemFromList (Collection<T1> collection, Class<T2> itemClass,
                                           int condition, Hero hero)
  {
    ArrayList<T2> items = Game.getByClass (collection, itemClass);
    ArrayList<T2> items2 = new ArrayList<T2>();
    items2.addAll (items);

    for (T2 item: items)
      if (!canBeUsed ((Item) item, condition))
        items2.remove (item);

    if (items2.isEmpty())
      System.out.println (ANSI_RED + messages[MSG_TYPE_NO_ITEMS] + ANSI_RESET);
    else
    {
      Item item = (Item) pickItemFromList (items2);

      if (item == null)
        return;

      useItem (hero, item);
    }
  }

  // Uses the default method to check if an item can be used.
  protected boolean canBeUsed (Item item, int condition)
  {
    return ((Usable) item).canBeUsed (condition);
  }

  // Uses an item by passing control to the hero that's using it.
  protected void useItem (Hero hero, Item item)
  {
    hero.useItem ((Usable) item);
  }
}
