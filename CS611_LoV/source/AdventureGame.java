
package lmh;

import java.util.*;

// This class represents an adventure genre of the game type and holds values
// that all adventure games have in common: a map and the position coordinates.
public abstract class AdventureGame extends Game
{
  protected Map map;
  protected int row, col;

  public AdventureGame (int width, int height, int row, int col)
  {
    map = new Map (width, height, row, col);
    this.row = row;
    this.col = col;
  }

  public void nextMove()
  {
    return;
  }

  public abstract void mapMove (int row, int col);

//  public abstract void mapMove(int row, int col, String creatureType);
}
