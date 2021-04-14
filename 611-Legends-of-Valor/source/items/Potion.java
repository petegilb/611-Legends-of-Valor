
package lmh.items;

// Represents a potion item.
public class Potion extends UsableItem
{
  protected int[] attIncrease;

  public Potion()
  {
    this (new String[]{ "unknown potion", "0", "0", "0", "All" });
  }

  public Potion (String atts[])
  {
    super (atts[0], Integer.parseInt(atts[1]), Integer.parseInt(atts[2]),
           "Potion", 1);

    attIncrease = new int[]{ 0, 0, 0, 0, 0 };

    String[] props = atts[4].split("/");

    for (int i = 0; i < props.length; i++)
      switch (props[i])
      {
        case "Health":
          attIncrease[3] = Integer.parseInt(atts[3]);
          break;
        case "Strength":
          attIncrease[0] = Integer.parseInt(atts[3]);
          break;
        case "Mana":
          attIncrease[4] = Integer.parseInt(atts[3]);
          break;
        case "Agility":
          attIncrease[2] = Integer.parseInt(atts[3]);
          break;
        case "Dexterity":
          attIncrease[1] = Integer.parseInt(atts[3]);
          break;
        case "All":
          for (int j = 0; j < attIncrease.length; j++)
            attIncrease[j] = Integer.parseInt(atts[3]);
          break;
      }
  }

  public void setAttIncrease (int[] atts)
  {
    this.attIncrease = atts;
  }

  public int[] getAttIncrease()
  {
    return attIncrease;
  }

  public String toString()
  {
    String output = "";
    String[] names = new String [] { "Strength", "Dexterity", "Agility",
                                  "Health", "Mana" };

    for (int i = 0; i < attIncrease.length; i++)
      if (attIncrease[i] > 0)
        output += ", " + names[i] + ": +" + attIncrease[i];

    return super.toString() + output;
  }
}
