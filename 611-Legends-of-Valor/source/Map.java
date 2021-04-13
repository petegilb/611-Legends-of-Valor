
package lmh;

import java.util.*;
import lmh.interfaces.*;

// A class for the game map. Initializes all the cells randomly. Displays
// the legend and the menu. Shows hero party position on the map.
public class Map implements Drawable
{
  private final int[] dx = new int[]{1, -1, 0, 0, 1, -1, -1, 1};
  private final int[] dy = new int[]{0, 0, 1, -1, -1, -1, 1, 1};
  private final int BLOCKED_CELL_RATIO = 20,
                    MARKET_CELL_RATIO = 30,
                    COMMON_CELL_RATIO = 50;

  private int width, height;
  private Cell[][] map;

  public Map ()
  {
    this (8, 8, 0, 0);
  }

  public Map (int width, int height, int row, int col)
  {
    int mapSize = width * height;

    int numBlockedCells = Math.round (mapSize * BLOCKED_CELL_RATIO / (float) 100),
        numMarketCells = Math.round (mapSize * MARKET_CELL_RATIO / (float) 100),
        numCommonCells = Math.round (mapSize * COMMON_CELL_RATIO / (float) 100);

    List<Cell> blockedCells = new ArrayList<Cell>(numBlockedCells);
    List<Cell> marketCells = new ArrayList<Cell>(numMarketCells);
    List<Cell> commonCells = new ArrayList<Cell>(numCommonCells);

    for (int i = 0; i < numBlockedCells; i++)
      blockedCells.add (new Cell(Cell.TYPE_BLOCKED));

    for (int i = 0; i < numMarketCells; i++)
      marketCells.add (new Cell(Cell.TYPE_MARKET));

    for (int i = 0; i < numCommonCells; i++)
      commonCells.add (new Cell(Cell.TYPE_COMMON));

    LinkedList<Cell> cells = new LinkedList<Cell>();
    cells.addAll (blockedCells);
    cells.addAll (marketCells);
    cells.addAll (commonCells);
    Collections.shuffle (cells);

    map = new Cell[height][width];
    this.width = width;
    this.height = height;

    for (int i = 0; i < height; i++)
    {
      for (int j = 0; j < width; j++)
        map[i][j] = cells.removeFirst();

      map[i][width-1].setEndOfRow(true);
    }

    map[row][col].setDisplayValue (Cell.TYPE_HEROES);
  }

  public Cell getCell (int row, int col)
  {
    if (row < 0 || col < 0 || row >= height || col >= width)
      return null;

    Cell cell = map[row][col];

    if (cell.getDisplayValue() == Cell.TYPE_HEROES){
      return cell;
    }


    if (cell.getType() == Cell.TYPE_BLOCKED)
      return null;

    return cell;
  }

  public void draw()
  {
    System.out.println();

    for (int i = 0; i < height; i++)
    {
      for (int j = 0; j < width; j++)
        System.out.print("+---");

      System.out.println("+");

      for (int j = 0; j < width; j++)
        map[i][j].draw();
    }

    for (int i = 0; i < width; i++)
      System.out.print("+---");

    System.out.println("+");
    displayLegendAndMenu();
  }

  private void displayLegendAndMenu ()
  {
    System.out.print("\nSquares marked with " + Cell.TYPE_BLOCKED + " are not accessible. ");
    System.out.print("Squares marked with " + Cell.TYPE_MARKET + " are marketplaces. ");
    System.out.print("Empty squares are regular squares where combat is possible. ");
    System.out.println("Current hero party position is shown by " + Cell.TYPE_HEROES);

    System.out.println("\nThe following actions are available: " +
                       "W - move up, A - move left, S - move down, " +
                       "D - move right, I - show inventory, Q - quit game");

    System.out.println();
  }

  public int getWidth(){ return this.width; }
  public int getHeight(){ return this.height; }
}
