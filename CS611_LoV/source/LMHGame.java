
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
  private final String GAME_LOGO = " _                              _              __  __     __    _            \n" +
          "| |    ___  __ _  ___ _ __   __| |___    ___  / _| \\ \\   / /_ _| | ___  _ __ \n" +
          "| |   / _ \\/ _` |/ _ \\ '_ \\ / _` / __|  / _ \\| |_   \\ \\ / / _` | |/ _ \\| '__|\n" +
          "| |__|  __/ (_| |  __/ | | | (_| \\__ \\ | (_) |  _|   \\ V / (_| | | (_) | |   \n" +
          "|_____\\___|\\__, |\\___|_| |_|\\__,_|___/  \\___/|_|      \\_/ \\__,_|_|\\___/|_|   \n" +
          "           |___/                                                             ";

  private final String GAME_RULES = "Legends of Valor is played in a 8x8 grid. The grid is divided in three lanes as\n" +
          "Each lane has a width of two cells. Each cell itself has a width of\n" +
          "two. More specifically, this means that in every cell there can be either no one, one\n" +
          "hero, one monster, or one hero and one monster, but never two heroes or two\n" +
          "monsters.  The first and the last\n" +
          "row will represent a Nexus. The first row will be the Nexus for monsters while the last\n" +
          "will be the Nexus for heroes. A Nexus can be thought of as the headquarters of a " + "team. ";

  private final int COMBAT_CHANCE = 50;
  private ArrayList<Hero> heroes;

  public LMHGame()
  {
    this (8, 8, 1, 1); // 8x8 map and starting position not in the corner to avoid being trapped.
  }

  public LMHGame (int width, int height, int row, int col)
  {
    super (width, height, row, col);
    displayIntro();
    System.out.print ("How many heroes do you want to play with? " );
    int numHeroes = Integer.parseInt(Game.getInput(new String[]{ "1", "2", "3" }));
    String[] heroClasses = { "Warrior", "Paladin", "Sorcerer" };
    heroes = new ArrayList<Hero>(numHeroes);

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
      heroes.add (hero);
    }
  }

//  @Override
//  public void mapMove(int row, int col) {
//
//  }

// TODO: 2021/4/13 check cells neighbors to find whether there have to have a fight.
// TODO: 2021/4/13 find cells which are available
  public void play()
  {
    // TODO: 2021/4/14 for convenience, I just initiate the monster/hero at fixed postion, Better idea is to use respawn() method
    while (true)
    {
      map.draw();
      System.out.print("Please input your next move: ");
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

  public void displayIntro()
  {
    System.out.println();
    System.out.println(GAME_LOGO);
//    System.out.println ("LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH");
//    System.out.println ("*                                                       *");
//    System.out.println ("*         **           **       **    **     **         *");
//    System.out.println ("*         **           ***     ***    **     **         *");
//    System.out.println ("*         **           ** *   * **    ** *** **         *");
//    System.out.println ("*         **           **   *   **    **     **         *");
//    System.out.println ("*         *********    **       **    **     **         *");
//    System.out.println ("*                                                       *");
//    System.out.println ("***LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH***LMH***");
    System.out.println();
    System.out.println("Welcome to the game of \"Legends: Monsters and Heroes\"!");
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
