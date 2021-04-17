
package lmh;

import java.util.*;
import lmh.interfaces.*;

// A class for the game map. Initializes all the cells randomly. Displays
// the legend and the menu. Shows hero party position on the map.
public class Map implements Drawable
{
  public static final String ANSI_RESET ="\u001B[0m";
  public static final String ANSI_BLACK ="\u001B[30m";
  public static final String ANSI_RED ="\u001B[31m";
  public static final String ANSI_GREEN ="\u001B[32m";
  public static final String ANSI_YELLOW ="\u001B[33m";
  public static final String ANSI_BLUE ="\u001B[34m";
  public static final String ANSI_PURPLE ="\u001B[35m";
  public static final String ANSI_CYAN ="\u001B[36m";
  public static final String ANSI_WHITE ="\u001B[37m";

  private final String ACTION_INFOS = "\nThe following actions are available: \n" +
          "W - move up, A - move left, S - move down, \n" +
          "D - move right, I - show inventory, Q - quit game\n" +
          "Q - attack, C - cast a spell" + "\n" + "B - go back to nexus, T - Teleport other lane";

  private final String CELL_INFOS = "\nSquares marked with "+ ANSI_WHITE +  Cell.TYPE_BLOCKED + " are " + ANSI_WHITE + "not accessible" + ANSI_RESET + "\n" +
          "Squares marked with " + ANSI_RED + Cell.TYPE_MARKET + ANSI_RESET + " are " + ANSI_RED + "marketplaces" + ANSI_RESET + "\n" +
          "Squares marked with " + ANSI_GREEN + Cell.TYPE_BUSH +   ANSI_RESET  + " are " + ANSI_GREEN + "BushCells" + ANSI_RESET + "\n" +
          "Squares marked with " + ANSI_CYAN + Cell.TYPE_CAVE +   ANSI_RESET + " are " + ANSI_CYAN + "CaveCells" + ANSI_RESET + "\n" +
          "Squares marked with " + ANSI_YELLOW + Cell.TYPE_KOULOU + ANSI_RESET + " are " + ANSI_YELLOW + " KouluCells" + ANSI_RESET + "\n" +
          "Empty squares are regular squares where combat is possible. \n" +
          "Current hero party position is shown by " + Cell.TYPE_HEROES +  "\n";

  private final int BLOCKED_CELL_RATIO = 25, //should be 16
                    NEXUS_CELL_RATIO = 19, //should be 12
                    KOULOU_CELL_RATIO = 14,
                    CAVE_CELL_RATIO = 14,
                    BUSH_CELL_RATIO = 14,
                    COMMON_CELL_RATIO = 14;

  private int width, height;
  private Cell[][] map;

  public Map ()
  {
    this (8, 8, 0, 0);
  }

  public Map (int width, int height, int row, int col)
  {

    int mapSize = width * height;

    //assuming we have an 8x8 grid fixed for Legends of Valor...
    //blocked cells are in between the "lanes"
    int numBlockedCells = Math.round (mapSize * BLOCKED_CELL_RATIO / (float) 100),
            numBushCells = Math.round(mapSize * BUSH_CELL_RATIO / (float) 100),
            numKoulouCells = Math.round(mapSize * KOULOU_CELL_RATIO / (float) 100),
            numCaveCells = Math.round(mapSize * CAVE_CELL_RATIO / (float) 100),
            numNexusCells = Math.round (mapSize * NEXUS_CELL_RATIO / (float) 100),
            numCommonCells = Math.round (mapSize * COMMON_CELL_RATIO / (float) 100);

    //create lists of each type of cell
    List<Cell> blockedCells = new ArrayList<Cell>(numBlockedCells);
    List<Cell> nexusCells = new ArrayList<Cell>(numNexusCells);
    List<Cell> commonCells = new ArrayList<Cell>(numCommonCells);
    List<Cell> caveCells = new ArrayList<Cell>(numCaveCells);
    List<Cell> bushCells = new ArrayList<Cell>(numBushCells);
    List<Cell> koulouCells = new ArrayList<Cell>(numKoulouCells);

    //populate those lists
    for (int i = 0; i < numBlockedCells; i++)
      blockedCells.add (new Cell(Cell.TYPE_BLOCKED));

    for (int i = 0; i < numNexusCells; i++)
      nexusCells.add (new Cell(Cell.TYPE_NEXUS));

    for (int i = 0; i < numCommonCells; i++)
      commonCells.add (new Cell(Cell.TYPE_COMMON));

    for (int i = 0; i < numCaveCells; i++)
      caveCells.add (new Cell(Cell.TYPE_CAVE));

    for (int i = 0; i < numBushCells; i++)
      bushCells.add (new Cell(Cell.TYPE_BUSH));

    for (int i = 0; i < numKoulouCells; i++)
      koulouCells.add (new Cell(Cell.TYPE_KOULOU));

    //make a linkedlist that we will then shuffle for the non-preset tiles
    LinkedList<Cell> cells = new LinkedList<Cell>();
    cells.addAll (caveCells);
    cells.addAll (bushCells);
    cells.addAll (koulouCells);
    cells.addAll (commonCells);
    Collections.shuffle (cells);

    map = new Cell[height][width];
    this.width = width;
    this.height = height;

    //set the tiles in the board itself
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // get the cell num
        if(i == 0 || i == height-1){ //if it's the first or the last row
          if(j == 2 || j == 5){ //assuming the grid is 8x8 for now
            //if it's a blocked column
            map[i][j] = new Cell(Cell.TYPE_BLOCKED);
          }
          else{ //otherwise it's a nexus
            map[i][j] = new Cell(Cell.TYPE_NEXUS);
          }

        }
        else if(j == 2 || j==5){ //if it's a blocked column
          map[i][j] = new Cell(Cell.TYPE_BLOCKED);
        }
        else{
          map[i][j] = cells.removeFirst();
        }
      }
      map[i][width-1].setEndOfRow(true);
    }

    map[row][col].setDisplayValue (Cell.TYPE_HEROES);
    setCellsNum();
  }

  private void setCellsNum() {
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        int cellNum = i * width + j;
        map[i][j].setCellNum(cellNum);
        System.out.print(" " + cellNum);
      }
    }
  }

  public Cell getCell (int row, int col)
  {
    if (row < 0 || col < 0 || row >= height || col >= width)
      return null;

    Cell cell = map[row][col];

    //if the current location is where the heroes are located
    if (cell.getDisplayValue() == Cell.TYPE_HEROES)
      return cell;

    if (cell.getType() == Cell.TYPE_BLOCKED)
      return null;

    return cell;
  }

  public void draw()
  {
    System.out.println();
    String spaces = "   ";

    for (int i = 0; i < height; i++) {
      if(i!=0){
        System.out.println("");
      }
      //for each cell first row
      for (int j = 0; j < width; j++){
        map[i][j].draw();
      }

      //for each cell second row
      for (int j = 0; j < width; j++){
        //if the cell is blocked
        if(map[i][j].getType() == map[i][j].TYPE_BLOCKED){
          System.out.print("| X X X |");
        }
        else if(map[i][j].getType() == Cell.TYPE_MONSTER || map[i][j].getType() == Cell.TYPE_HEROES){
          System.out.print("|   "+ map[i][j].getType() + "   |");
        }
        else{
          System.out.print("|       |");
        }

        //if there is a hero or monster in the first slot

        //in the second slot

        //if it's the end of the row add a space
        if(map[i][j].isEndOfRow() == false){
          System.out.print("   ");
        }
        else{
          System.out.print("\n");
        }
      }

      //for each cell third row
      for (int j = 0; j < width; j++){
        map[i][j].draw();
      }
    }

//    for (int i = 0; i < width; i++){
//      System.out.print("+---");
//    }

    //System.out.println("+");
    //showCellsNum(); // display the cell num.
    displayLegendAndMenu();
  }

  //to be updated...
  private void displayLegendAndMenu ()
  {
    System.out.print(CELL_INFOS);
//    System.out.print("Squares marked with " + Cell.TYPE_MARKET + " are marketplaces. \n");
//    System.out.print("Squares marked with " + Cell.TYPE_NEXUS + " are NexusCells. \n");
//    System.out.print("Squares marked with " + Cell.TYPE_BUSH + " are BushCells. \n");
//    System.out.print("Squares marked with " + Cell.TYPE_CAVE + " are CaveCells. \n");
//    System.out.print("Squares marked with " + Cell.TYPE_KOULOU + " are KouluCells. \n");
//    System.out.println("Empty squares are regular squares where combat is possible. ");

    //System.out.println("Current hero party position is shown by " + Cell.TYPE_HEROES);

    System.out.println("\n " + ACTION_INFOS);

    System.out.println();
  }


  public void showCellsNum(){
    for(int i = 0; i < width; i++){
      for(int j = 0; j < height; j++){
        System.out.print(map[i][j].getCellNum() + " ");
      }
      System.out.println();
    }
  }

  /**
   * check if the cell at given row and col is accessible
   * @param row
   * @param col
   * @return true if accessible;
   */
  public boolean isAccesibleCell(int row, int col){
    if(row < 0 || row >= this.height || col < 0 || col >= this.width)
      return false;
    if(map[row][col].getType() == Cell.TYPE_BLOCKED)
      return false;
    return true;
  }

  public int getWidth() { return width; }

  public int getHeight() { return width; }
}
