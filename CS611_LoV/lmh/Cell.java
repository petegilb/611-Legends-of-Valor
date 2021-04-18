
package lmh;

import lmh.creatures.Monster;
import lmh.interfaces.*;

import java.util.ArrayList;
import java.util.List;

// This class represents a cell on the map. Each cell holds its own state,
// draws and updates itself.
public class Cell implements Drawable, TextColors
{
  public static final String TYPE_BLOCKED = ANSI_RED + "X" + ANSI_RESET;
  public static final String TYPE_COMMON = "R"; // r for regular
  public static final String TYPE_MARKET = "$"; // $ represent for market
  public static final String TYPE_HEROES = "H";
  public static final String TYPE_MONSTER = "M";
  public static final String TYPE_BUSH = ANSI_YELLOW + "B" + ANSI_RESET;
  public static final String TYPE_CAVE = ANSI_BLUE + "C" + ANSI_RESET;
  public static final String TYPE_KOULOU = ANSI_GREEN + "K" + ANSI_RESET;
  public static final String TYPE_NEXUS = ANSI_PURPLE + "N" + ANSI_RESET;

  private boolean endOfRow;
  private String displayValue;
  private String type;

  private int cellNum; // cell num represent

  private List attackList; // stores all the cells which can be attack
  private List moveList; // store all the cells can move to

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
    this.attackList = new ArrayList();
    this.moveList = new ArrayList();
  }

  // assign the cells and give the cellNum
  public Cell(String type, int cellNum){
    this(type);
    this.cellNum = cellNum;
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
    String num = String.valueOf(getCellNum());
    output+= " - ";
    output+= type;
    output+= " - ";
    output+= type;

    if (endOfRow == false)
      output+= "   ";
    else{
      output+="\n";
    }

    System.out.print(output);
  }

  public void findAttackCells(Map map){

    int row = cellNum / map.getWidth();
    int col = cellNum % map.getWidth();
    int[] dx = {1, -1, 0, 0,  1, -1, 1 -1};
    int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};

    // search in 8 directions
    for(int dir = 0; dir < 8; dir++){
      int nx = row + dx[dir];
      int ny = col + dy[dir];
      //check isValid pos
      if(!map.isAccesibleCell(nx, ny)) // inaccessible cells cannot attack
        continue;
      if(this.getType() == Cell.TYPE_HEROES && map.getCell(nx, ny).getType() == TYPE_MONSTER){
        this.attackList.add(map.getCell(nx, ny));
      }
      if(this.getType() == Cell.TYPE_MONSTER && map.getCell(nx, ny).getType() == TYPE_HEROES) {
        this.attackList.add(map.getCell(nx, ny));
      }
    }
  }


  public void findMovableCells(Map map){
      if(this.getType() == TYPE_HEROES){
        heroCellMove(map);
      }
      else if(this.getType() == TYPE_MONSTER){
        monsterCellMove(map);
      }
  }

  /**
   * Monster starts from top can only move downward, left, right
   * @param map
   */
  private void monsterCellMove(Map map){
    int row = cellNum / map.getWidth();
    int col = cellNum % map.getWidth();
    boolean canMoveDown = true;
    int[] dx = { 0, 0, 1};
    int[] dy = {-1, 1, 0};

    for(int dir = 0; dir < 2; dir++){
      int nx = row + dx[dir];
      int ny = col + dy[dir];
      if(map.isAccesibleCell(nx, ny)){
        moveList.add(map.getCell(nx, ny));
      }
      if(map.getCell(nx, ny).getType() == TYPE_HEROES){
        canMoveDown = false;
      }
    }
    if(canMoveDown){
      moveList.add(map.getCell(row + dx[2], col + dy[2]));
    }
  }

  /**
   * Heroes starts from bottom , can only move upward, left, right
   * @param map
   */
  private void heroCellMove(Map map){
    int row = cellNum / map.getWidth();
    int col = cellNum % map.getWidth();
    boolean canMoveUp = true;
    int[] dx = { 0, 0, -1};
    int[] dy = {-1, 1, 0};

    for(int dir = 0; dir < 2; dir++){
      int nx = row + dx[dir];
      int ny = col + dy[dir];
      if(map.isAccesibleCell(nx, ny)){
        moveList.add(map.getCell(nx, ny));
      }
      if(map.getCell(nx, ny).getType() == TYPE_MONSTER){
        canMoveUp = false;
      }
    }
    if(canMoveUp){
      moveList.add(map.getCell(row + dx[2], col + dy[2]));
    }
  }
  /**
   * Set Cell Num
   * @param cellNum
   */
  public void setCellNum(int cellNum){this.cellNum = cellNum;}

  /**
   * get the cell num
   * @return
   */
  public int getCellNum(){ return this.cellNum; }

  public boolean canMove(Map map){
    if(moveList.size() != 0){ return true; }
    findMovableCells(map);
    return this.moveList.size() != 0;
  }

  public boolean canAttack(Map map){
    if(attackList.size() != 0){ return true; }
    findMovableCells(map);
    return this.moveList.size() != 0;
  }
}
