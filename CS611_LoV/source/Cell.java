
package lmh;

import lmh.interfaces.*;

// This class represents a cell on the map. Each cell holds its own state,
// draws and updates itself.
public class Cell implements Drawable
{
  public static final String TYPE_BLOCKED = "X";
  public static final String TYPE_COMMON = "R"; //r for regular
  public static final String TYPE_MARKET = "M";
  public static final String TYPE_HEROES = "H";
  public static final String TYPE_BUSH = "B";
  public static final String TYPE_CAVE = "C";
  public static final String TYPE_KOULOU = "K";
  public static final String TYPE_NEXUS = "N";

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

//    if (displayValue != null){
//      output = displayValue;
//    }
//    else{
//      output+= " - ";
//      output+= type;
//      output+= " - ";
//      output+= type;
//    }
    output+= " - ";
    output+= type;
    output+= " - ";
    output+= type;

    if (endOfRow == false)
      output+="   ";
    else{
      output+="\n";
    }

    System.out.print(output);
  }
}
