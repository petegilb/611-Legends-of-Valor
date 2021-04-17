
package lmh.items;

// Represents a lightning spell item in the game.
public class LightningSpell extends Spell
{
  public LightningSpell()
  {
    this (new String[]{ "unknown lightning spell", "0", "0", "0", "0" });
  }

  public LightningSpell (String atts[])
  {
    super (atts[0], Integer.parseInt(atts[1]), Integer.parseInt(atts[2]),
           "Lightning Spell", 1, Integer.parseInt(atts[3]), Integer.parseInt(atts[4]),
           new int[]{ 0, 0, 10 });
  }
}
