
package lmh.items;

// Represents a fire spell item in the game.
public class FireSpell extends Spell
{
  public FireSpell ()
  {
    this (new String[]{ "unknown fire spell", "0", "0", "0", "0" });
  }

  public FireSpell (String atts[])
  {
    super (atts[0], Integer.parseInt(atts[1]), Integer.parseInt(atts[2]),
           "Fire Spell", 1, Integer.parseInt(atts[3]), Integer.parseInt(atts[4]),
           new int[]{ 0, 10, 0 });
  }
}
