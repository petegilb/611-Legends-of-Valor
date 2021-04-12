
package lmh.items;

import lmh.interfaces.*;

// An armor is a type of a usable item that can be equipped.
public class Armor extends UsableItem implements Equipment
{
  protected int damageReduction;

  public Armor()
  {
    this (new String[]{ "unknown armor", "0", "0", "0" });
  }

  public Armor (String[] atts)
  {
    super (atts[0], Integer.parseInt(atts[1]), Integer.parseInt(atts[2]), "Armor", -1);
    damageReduction = Integer.parseInt(atts[3]);
  }

  public void setDamRed (int damRed)
  {
    this.damageReduction = damRed;
  }

  public int getDamRed()
  {
    return damageReduction;
  }

  public String toString ()
  {
    return super.toString() + ", Damage reduction: " + damageReduction;
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
