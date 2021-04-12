
package lmh.creatures;

// This class represents an exoskeleton monster.
public class Exoskeleton extends Monster
{
  public Exoskeleton ()
  {
    this (new String[]{ "unknown exoskeleton", "0", "0", "0", "0" });
  }

  public Exoskeleton (String[] atts)
  {
    super (atts[0], Integer.parseInt(atts[1]), "Exoskeleton", Integer.parseInt(atts[2]),
    Integer.parseInt(atts[3]), Integer.parseInt(atts[4]));
  }

  // Needed to create a deep copy to use in combat.
  public Object clone() throws CloneNotSupportedException
  {
    return super.clone();
  }
}
