
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

  public void play()
  {
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
