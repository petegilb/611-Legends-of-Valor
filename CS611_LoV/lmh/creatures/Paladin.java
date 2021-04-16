
package lmh.creatures;

// A class that represents the paladin hero class.
public class Paladin extends Hero
{
  public Paladin ()
  {
    this (new String[]{ "unknown paladin", "0", "0", "0", "0", "0", "0" });
  }

  public Paladin (String[] atts)
  {
    super (atts[0], "Paladin", Integer.parseInt(atts[1]), Integer.parseInt(atts[2]),
           Integer.parseInt(atts[3]), Integer.parseInt(atts[4]),
           Integer.parseInt(atts[5]), Integer.parseInt(atts[6]),
           new float[]{ 1.1f, 1.1f, 1.05f });
  }
}
