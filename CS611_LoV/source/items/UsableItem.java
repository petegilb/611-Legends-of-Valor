
package lmh.items;

import lmh.interfaces.*;

// Represents a special type of item that can be used.
public abstract class UsableItem extends Item implements Usable
{
  protected int charges;

  public UsableItem()
  {
    this ("unknown item", 0, 0, "Item", 0);
  }

  public UsableItem (String name, int goldPrice, int minLevel, String type,
                         int charges)
  {
    super (name, goldPrice, minLevel, type);
    this.charges = charges;
  }

  public void setCharges (int charges)
  {
    this.charges = charges;
  }

  public int getCharges()
  {
    return charges;
  }

  public void use()
  {
    charges -= 1;
  }

  public boolean canBeUsed (int heroLevel)
  {
    if (minLevel > heroLevel || isEmpty())
      return false;

    return true;
  }

  public boolean isEmpty()
  {
    return (charges == 0);
  }

  public String toString()
  {
    return super.toString();
  }
}
