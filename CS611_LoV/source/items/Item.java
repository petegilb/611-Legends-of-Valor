
package lmh.items;

import lmh.interfaces.*;

// The parent class of all items in the game.
// The item is any type of item found in the game: potion/spell/armor/weapon.
public abstract class Item implements Tradeable
{
  protected String name;
  protected int goldPrice;
  protected int minLevel;
  protected String type;

  public Item()
  {
    this ("unknown item", 0, 0, "Item");
  }

  public Item (String name, int goldPrice, int minLevel, String type)
  {
    this.name = name;
    this.goldPrice = goldPrice;
    this.minLevel = minLevel;
    this.type = type;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void setGoldPrice(int level)
  {
    this.goldPrice = goldPrice;
  }

  public int getGoldPrice()
  {
    return goldPrice;
  }

  public void setMinLevel(int minLevel)
  {
    this.minLevel = minLevel;
  }

  public int getMinLevel()
  {
    return minLevel;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
  }

  public String toString()
  {
     return name + ", Type: " + type +  ", Price: " + goldPrice +
            ", Required level: " + minLevel;
  }

  public void buy()
  {
    return;
  }

  public void sell()
  {
    return;
  }

  public boolean canBeBought (int goldAmount)
  {
    if (goldPrice > goldAmount)
      return false;

    return true;
  }

  public boolean canBeSold()
  {
    if (name == "Bare Skin" || name == "Bare Hands")
      return false;

    return true;
  }
}
