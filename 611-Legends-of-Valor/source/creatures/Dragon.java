
package lmh.creatures;

import lmh.interfaces.Move;

// This class represents a dragon monster.
public class Dragon extends Monster
{
  public Dragon ()
  {
    this (new String[]{ "unknown dragon", "0", "0", "0", "0" });
  }

  public Dragon (String[] atts)
  {
    super (atts[0], Integer.parseInt(atts[1]), "Dragon", Integer.parseInt(atts[2]),
    Integer.parseInt(atts[3]), Integer.parseInt(atts[4]));
  }

  // Needed to make a deep copy to use in combat.
  public Object clone() throws CloneNotSupportedException
  {
    return super.clone();
  }
}
