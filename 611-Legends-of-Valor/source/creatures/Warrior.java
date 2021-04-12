
package lmh.creatures;

// A class that represents the warrior hero class.
public class Warrior extends Hero
{
  public Warrior ()
  {
    this (new String[]{ "unknown warrior", "0", "0", "0", "0", "0", "0" });
  }

  public Warrior (String[] atts)
  {
    super (atts[0], "Warrior", Integer.parseInt(atts[1]), Integer.parseInt(atts[2]),
           Integer.parseInt(atts[3]), Integer.parseInt(atts[4]),
           Integer.parseInt(atts[5]), Integer.parseInt(atts[6]),
           new float[]{ 1.1f, 1.05f, 1.1f });
  }
}
