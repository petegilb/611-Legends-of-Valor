
package lmh.creatures;

import lmh.Cell;
import lmh.Map;
import lmh.actions.MarketAction;
import lmh.interfaces.Move;
import lmh.locations.Combat;

import java.util.*;

// A class that represents all creatures in the game: heroes and monsters and
// any other creatures that might be added in any future expansions.
public abstract class Creature implements Cloneable, Move
{
  protected String name;
  protected String type;
  protected int level;
  protected int health;

  // current location at map
  protected int row, col;
  protected Map map;

  protected Cell currCell; // current location

  public Creature()
  {
    this ("unknown creature", "Creature", 1);

  }

  public Creature (String name, String type, int level)
  {
    this.name = name;
    this.type = type;
    this.level = level;
    health = level*100;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void setType (String type)
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
  }

  public void setLevel (int level)
  {
    this.level = level;
  }

  public int getLevel()
  {
    return level;
  }

  public void setHealth (int health)
  {
    this.health = health;
  }

  public int getHealth()
  {
    return health;
  }

  public String toString()
  {
    return name + ", Type: " + type + ", Level: " + level + ", Health: " + health;
  }

  public boolean isFainted()
  {
    return (health <= 0);
  }

  // Needed to create a deep copy of a creature to use in combat.
  // This allows for not resetting them to previous state after combat.
  public Object clone() throws CloneNotSupportedException
  {
    return super.clone();
  }

  // TODO: 2021/4/12 implement Move interface

  /**
   * find all the neigbors of this creature;
   * @return
   */
  public List<Cell> getNeighbors(){
    int[] dx = {1, 1, 1, -1, -1, -1, 0, 0};
    int[] dy = {0, 1, -1, 1, -1, 0, 1, -1};
    List<Cell> neighbors = new ArrayList<>();
    for(int dir = 0; dir < 8; dir++){
      int nx = this.row + dx[dir];
      int ny = this.col + dy[dir];
      if(nx < 0 || nx >= map.getHeight() || ny < 0 || ny >= map.getWidth())
        continue;
      Cell neighbor = map.getCell(nx,ny);
      if(neighbor.getType() == Cell.TYPE_BLOCKED ||
         neighbor.getType() == Cell.TYPE_MARKET ||
         neighbor.getType() == Cell.TYPE_COMMON){

      }
      neighbors.add(neighbor);
    }
    return neighbors;
  }

  public void makeMovement(int row, int col){};

  public void printNeighborInfo(){};
}
