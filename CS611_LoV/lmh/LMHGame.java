
package lmh;

import java.util.*;
import lmh.actions.*;
import lmh.creatures.*;
import lmh.locations.*;
import lmh.interfaces.*;
import lmh.items.*;

// The main class that runs the game. Contains all the necessary logic to start,
// advance and end the game.
public class LMHGame extends AdventureGame implements TextColors
{
  private final int COMBAT_CHANCE = 50;
  private ArrayList<Hero> heroes;
  private ArrayList<Monster> monsters;
  private int maxLevel;

  //locations where heroes can spawn -> indexes correspond to each other
  private final int[] spawnLocY = new int[]{0, 3, 6};

  public LMHGame()
  {
    this (8, 8, 1, 1); // 8x8 map and starting position not in the corner to avoid being trapped.
  }

  public LMHGame (int width, int height, int row, int col)
  {
    super (width, height, row, col);
    displayIntro();
    //System.out.print ("How many heroes do you want to play with? " );
    //int numHeroes = Integer.parseInt(Game.getInput(new String[]{ "1", "2", "3" }));
    final int numHeroes = 3; //always 3 heroes
    String[] heroClasses = { "Warrior", "Paladin", "Sorcerer" };
    heroes = new ArrayList<Hero>(numHeroes);
    monsters = new ArrayList<Monster>(numHeroes);
    maxLevel = 0;

    //initialize heroes
    for (int i = 0; i < numHeroes; i++)
    {
      Class heroClass = null;

      while (heroClass == null)
      {
        System.out.println();
        heroClass = (new PickHeroClassAction()).getHeroClass (heroClasses);
      }

      ArrayList<Hero> allClassHeroes =
              getByClass (GameFiles.getInstance().getAllObjects(), heroClass);

      ArrayList<Hero> tempHeroes = new ArrayList<Hero>();
      tempHeroes.addAll (allClassHeroes);

      for (Hero hero: heroes)
        if (tempHeroes.contains (hero))
          allClassHeroes.remove (hero);

      Hero hero = null;

      while (hero == null)
      {
        System.out.println();
        hero = (new PickHeroAction()).getHero (allClassHeroes);
      }
      //get max hero level so we can spawn the monsters
      int heroLevel = hero.getLevel();
      if(heroLevel > maxLevel)
          maxLevel = heroLevel;

      //set the position of the hero
      hero.setPos(map.getHeight()-1, spawnLocY[i]);
      map.updateCell(hero);
      heroes.add (hero);
    }

    //initialize monsters
    monsters.addAll(spawnMonsters(maxLevel, numHeroes));

  }

// TODO: 2021/4/13 check cells neighbors to find whether there have to have a fight.
// TODO: 2021/4/13 find cells which are available
  public void play()
  {
    // TODO: 2021/4/14 for convenience, I just initiate the monster/hero at fixed postion, Better idea is to use respawn() method
    while (true) {
      map.draw();
      //get the input from the player each turn
      //need to cycle through heroes but most of the actions are meant for the group so we need to change that
      for(Hero hero : heroes){
        //if they're alive

        //do this
        System.out.println("Now controlling " + hero.getName());
        System.out.println("Please input your next move: ");
        String action = Game.getInput (new String[]{ "M", "A", "B", "T", "I", "Q" });
        switch (action)
        {
          case "M": // move up

            break;
          case "A": // move left

            break;
          case "B": // move down

            break;
          case "T": // move right

            break;
          case "I": //show inventory
            (new InventoryAction (heroes)).useLocation();
            break;
          case "Q": // quit game, display heroes and their stats
            quit();
            return;
        }
      }


    }
  }

  public void moveHero(){
    String action = Game.getInput (new String[]{ "W", "A", "S", "D", "I", "Q" });

    switch (action)
    {
      case "W": // move up
        mapMove (row-1, col);
        break;
      case "A": // move left
        mapMove (row, col-1);
        break;
      case "S": // move down
        mapMove (row+1, col);
        break;
      case "D": // move right
        mapMove (row, col+1);
        break;
      case "I": //show inventory
        (new InventoryAction (heroes)).useLocation();
        break;
      case "Q": // quit game, display heroes and their stats
        quit();
        return;
    }
  }

  public void mapMove (int row, int col)
  {
    Cell cell = map.getCell (row, col);

    if (cell == null)
    {
      System.out.println(ANSI_RED + "\nThat terrain is not accessible!" + ANSI_RESET);
      return;
    }

    map.getCell (this.row, this.col).setDisplayValue (null);
    cell.setDisplayValue (Cell.TYPE_HEROES);
    this.row = row;
    this.col = col;
    map.draw();

    if (cell.getType() == Cell.TYPE_MARKET)
      (new MarketAction (heroes)).useLocation();
    else // Cell.TYPE_COMMON
    {
      Random rand = new Random();

      if (rand.nextInt(100) < COMBAT_CHANCE)
      {
        Combat combat = new Combat (heroes);
        combat.execute();
      }
    }
  }

  // Spawns monsters of the same level as the highest level of the hero party.
  private ArrayList<Monster> spawnMonsters (int level, int number)
  {
    // Get all monsters from game files.
    List<Monster> allMonsters = Game.getByClass (GameFiles.getInstance().getAllObjects(), Monster.class);
    List<Monster> levelMonsters = new ArrayList<Monster>();
    ArrayList<Monster> monsters = new ArrayList<Monster>();
    Random rand = new Random();

    // Get all monsters of the same level as the highest hero level.
    for (Monster monster: allMonsters)
      if (monster.getLevel() == level)
        levelMonsters.add (monster);

    for (int i = 0; i < number; i++)
    {
      try
      {
        // Add a deep copy of a generated monster to this combat leaving the original intact
        Monster monster = (Monster) levelMonsters.get (rand.nextInt (levelMonsters.size())).clone();
        monster.setPos(0, spawnLocY[i]);
        map.updateCell(monster);
        monsters.add (monster);
      }
      catch (Exception e)
      {
        System.out.println (e);
      }
    }
    return monsters;
  }

  public void displayIntro()
  {
    System.out.println();
    System.out.println ("LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH");
    System.out.println ("*                                                       *");
    System.out.println ("*         **           **       **    **     **         *");
    System.out.println ("*         **           ***     ***    **     **         *");
    System.out.println ("*         **           ** *   * **    ** *** **         *");
    System.out.println ("*         **           **   *   **    **     **         *");
    System.out.println ("*         *********    **       **    **     **         *");
    System.out.println ("*                                                       *");
    System.out.println ("***LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH***");
    System.out.println();
    System.out.println("Welcome to the game of \"Legends of Valor\"!");
    System.out.println("The object of the game is to roam this holy land " +
                       "cleansing it of the horrific monsters that don't belong " +
                       "here!");
    System.out.println();
  }

  public void quit()
  {
    System.out.println("\n**************** Game Over ****************\n");
    System.out.println("Thank you for playing!\n");

    for (Hero hero: heroes)
      System.out.println(hero);

    System.out.println();
    System.out.println();
  }
}
