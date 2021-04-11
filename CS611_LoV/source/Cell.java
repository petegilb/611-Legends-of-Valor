
package lmh;

import lmh.interfaces.*;

// This class represents a cell on the map. Each cell holds its own state,
// draws and updates itself.
public class Cell implements Drawable
{
  public static final String TYPE_BLOCKED = "X";
  public static final String TYPE_COMMON = " ";
  public static final String TYPE_MARKET = "M";
  public static final String TYPE_HEROES = "H";

  private boolean endOfRow;
  private String displayValue;
  private String type;

  public Cell ()
  {
    this (Cell.TYPE_COMMON);
  }

  public Cell (String type)
  {
    this (false, null, type);
  }

  public Cell (boolean end, String mark, String type)
  {
    endOfRow = end;
    displayValue = mark;
    this.type = type;
  }

  public void setEndOfRow(boolean endOfRow)
  {
    this.endOfRow = endOfRow;
  }

  public boolean isEndOfRow()
  {
    return endOfRow;
  }

  public void setDisplayValue (String displayValue)
  {
    this.displayValue = displayValue;
  }

  public String getDisplayValue()
  {
    return displayValue;
  }

  protected void setType(String type)
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
  }

  public void draw()
  {
    String output = type;

    if (displayValue != null)
      output = displayValue;

    System.out.print("| " + output + " ");

    if (endOfRow)
      System.out.println("|");
  }
}
