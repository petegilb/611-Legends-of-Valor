
package lmh.locations;

import java.util.*;
import lmh.items.*;

// A class for all locations that holds their common state.
public abstract class Location
{
  private String name;
  protected ArrayList<Item> items;

  public Location()
  {
    this ("unknown location", new ArrayList<Item>());
  }

  public Location (String name, ArrayList<Item> items)
  {
    this.name = name;
    this.items = items;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void setItems (ArrayList<Item> items)
  {
    this.items = items;
  }

  public ArrayList<Item> getItems()
  {
    return items;
  }

  public void addItem (Item item)
  {
    items.add (item);
  }

  public void removeItem (Item item)
  {
    items.remove (item);
  }

  public boolean isEmpty()
  {
    return items.isEmpty();
  }

  public String toString()
  {
    return name;
  }
}
