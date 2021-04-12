
package lmh.creatures;

// A class that represents the sorcerer hero class.
public class Sorcerer extends Hero
{
  public Sorcerer ()
  {
    this (new String[]{ "unknown sorcerer", "0", "0", "0", "0", "0", "0" });
  }

  public Sorcerer (String[] atts)
  {
    super (atts[0], "Sorcerer", Integer.parseInt(atts[1]), Integer.parseInt(atts[2]),
           Integer.parseInt(atts[3]), Integer.parseInt(atts[4]),
           Integer.parseInt(atts[5]), Integer.parseInt(atts[6]),
           new float[]{ 1.05f, 1.1f, 1.1f });
  }
}
