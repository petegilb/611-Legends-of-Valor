
package lmh.creatures;

// A class that represents the spirit monster class.
public class Spirit extends Monster
{
  public Spirit ()
  {
    this (new String[]{ "unknown spirit", "0", "0", "0", "0" });
  }

  public Spirit (String[] atts)
  {
    super (atts[0], Integer.parseInt(atts[1]), "Spirit", Integer.parseInt(atts[2]),
    Integer.parseInt(atts[3]), Integer.parseInt(atts[4]));
  }

  // Needed to create a deep copy to use in combat.
  public Object clone() throws CloneNotSupportedException
  {
    return super.clone();
  }
}
