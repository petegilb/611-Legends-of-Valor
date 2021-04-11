
package lmh.items;

// Represents an ice spell item in the game.
public class IceSpell extends Spell
{
  public IceSpell ()
  {
    this (new String[]{ "unknown ice spell", "0", "0", "0", "0" });
  }

  public IceSpell (String atts[])
  {
    super (atts[0], Integer.parseInt(atts[1]), Integer.parseInt(atts[2]),
           "Ice Spell", 1, Integer.parseInt(atts[3]), Integer.parseInt(atts[4]),
           new int[]{ 10, 0, 0 });
  }
}
