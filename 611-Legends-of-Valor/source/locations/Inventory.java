
package lmh.locations;

import java.util.*;
import lmh.items.*;
import lmh.interfaces.*;

// Represents a hero's inventory and also a location when the player choose "I"
// option from the game menu.
public class Inventory extends Location implements Drawable
{
  public Inventory()
  {
    super ("inventory", new ArrayList<Item>());
  }

  public void draw()
  {
    if (isEmpty())
      System.out.println ("\tempty");

    for (Item item: items)
      System.out.println ("\t" + item);
  }
}
