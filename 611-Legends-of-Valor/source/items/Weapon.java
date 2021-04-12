
package lmh.items;

import lmh.interfaces.*;

// Represents a weapon which is a special type of a usable item that can be
// equipped.
public class Weapon extends UsableItem implements Equipment
{
  protected int damage;
  protected int hands;

  public Weapon()
  {
    this (new String[]{ "unknown weapon", "0", "0", "0", "0" });
  }

  public Weapon (String[] atts)
  {
    super (atts[0], Integer.parseInt(atts[1]), Integer.parseInt(atts[2]), "Weapon", -1);
    damage = Integer.parseInt(atts[3]);
    hands = Integer.parseInt(atts[4]);
  }

  public int getDamage ()
  {
    return damage;
  }

  public String toString ()
  {
    return super.toString() + ", Damage: " + damage + ", Hands required: " + hands;
  }

  public void equip()
  {
    use();
  }

  public boolean canBeEquipped (int condition)
  {
    return canBeUsed (condition);
  }
}
