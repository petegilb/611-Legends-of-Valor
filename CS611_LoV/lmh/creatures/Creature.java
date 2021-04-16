
package lmh.creatures;

// A class that represents all creatures in the game: heroes and monsters and
// any other creatures that might be added in any future expansions.
public abstract class Creature implements Cloneable
{
  protected String name;
  protected String type;
  protected int level;
  protected int health;
  protected int xLoc;
  protected int yLoc;

  public Creature()
  {
    this ("unknown creature", "Creature", 1);
  }

  public Creature (String name, String type, int level)
  {
    this.name = name;
    this.type = type;
    this.level = level;
    health = level * 100;
  }

  public void setPos(int x, int y){
    xLoc = x;
    yLoc = y;
  }

  public int getxLoc(){
    return xLoc;
  }

  public int getyLoc(){
    return yLoc;
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


}
