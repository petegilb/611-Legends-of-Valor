
package lmh.locations;

import java.util.*;
import java.lang.reflect.*;
import lmh.*;
import lmh.creatures.*;
import lmh.interfaces.*;

// Represents and drives combat in the game.
public class Combat extends Location
{
  private ArrayList<Fighter> attackers;
  private ArrayList<Fighter> defenders;

  private Fighter attacker;
  private Fighter defender;

  private ArrayList<Integer> startingHealth;
  private int monsterLevel;

  public Combat()
  {
    this (new ArrayList<Hero>());
  }

  public Combat (ArrayList<Hero> heroes)
  {
    super ("combat", null);
    System.out.println ("************* COMBAT *************");

    int maxLevel = 0;
    startingHealth = new ArrayList<Integer>();
    attackers = new ArrayList<Fighter>();

    // Records the initial health of the heros to restore after the combat.
    // Initializes the attackers array who will be heroes on the first turn.
    // Finds the maximum level of heroes in the party to generate monsters of
    // the same level.
    for (Hero hero: heroes)
    {
      int heroLevel = hero.getLevel();
      startingHealth.add (hero.getHealth());
      attackers.add (hero);

      if (heroLevel > maxLevel)
        maxLevel = heroLevel;
    }

    // Initializes the defenders array who will be monsters on the first turn.
    defenders = spawnMonsters(maxLevel, heroes.size());
    attacker = attackers.get(0);
    defender = defenders.get(0);
    monsterLevel = maxLevel;
  }

  // Spawns monsters of the same level as the highest level of the hero party.
  private ArrayList<Fighter> spawnMonsters (int level, int number)
  {
    // Get all monsters from game files.
    List<Monster> allMonsters = Game.getByClass (GameFiles.getInstance().getAllObjects(), Monster.class);
    List<Monster> levelMonsters = new ArrayList<Monster>();
    ArrayList<Fighter> monsters = new ArrayList<Fighter>();
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
        monsters.add ((Fighter) levelMonsters.get (rand.nextInt (levelMonsters.size())).clone());
      }
      catch (Exception e)
      {
        System.out.println (e);
      }
    }
    return monsters;
  }

  // Drives combat. Keeps passing the turn from heroes to monsters until one
  // party is fainted. Processes increases and decreases for each round/combat/level.
  public void execute()
  {
    while (true)
    {
      System.out.println ("\n\n---------- Attackers ----------\n");
      for (Fighter fighter: attackers)
        System.out.println (fighter);

      System.out.println ("\n---------- Defenders ----------\n");
      for (Fighter fighter: defenders)
        System.out.println (fighter);

      System.out.println ("\nNext, " + ((Creature) attacker).getName() + " will fight " +
                          ((Creature) defender).getName() + "!");
      System.out.println ("Press 'c' to continue...");
      Game.getInput (new String[]{ "C" });

      // Calls the appropriate method on a hero or monster depending on whose
      // turn it is.
      attacker.nextCombatRound (defender);

      // If a defender is fainted, checks if all creatures in their party fainted since
      // this is the only place in combat when it can stop.
      if (((Creature) defender).isFainted())
      {
        System.out.println (" and faints!");

        // If all creatures on the defending side fainted, then break the loop and process
        // the outcome.
        if (allFainted())
          break;
      }

      // After this round of combat is over, give this attacker a round increase.
      // Set the pointer to the next attacker and the next defender. Repeat.
      attacker.roundIncrease();
      defender = getNextDefender();
      attacker = getNextAttacker();
    }

    // After combat is over, process increases for the winners.
    for (Fighter winner: attackers)
      winner.combatIncrease (monsterLevel, startingHealth.get (attackers.indexOf(winner)));

    // After combat is over, process decreases for the losers.
    for (Fighter loser: defenders)
      loser.combatDecrease (startingHealth.get (defenders.indexOf(loser)));

    System.out.println ("************* COMBAT OVER *************");
  }

  // A method that finds and sets the next defender.
  private Fighter getNextDefender ()
  {
    int i = defenders.indexOf (defender);

    if (i == defenders.size()-1)
      defender = defenders.get(0);
    else
      defender = defenders.get(i+1);

    if (((Creature) defender).isFainted())
      defender = getNextDefender();

    return defender;
  }

  // A method that finds and sets the next attacker.
  private Fighter getNextAttacker ()
  {
    int i = attackers.indexOf (attacker);

    if (i == attackers.size()-1)
    {
      attacker = defenders.get(0);
      defender = attackers.get(0);
      ArrayList<Fighter> temp = attackers;
      attackers = defenders;
      defenders = temp;

      if (((Creature) defender).isFainted())
        defender = getNextDefender();
    }
    else
      attacker = attackers.get(i+1);

    if (((Creature) attacker).isFainted())
      attacker = getNextAttacker();

    return attacker;
  }

  // A method that checks if all creatures on the defending side fainted.
  private boolean allFainted()
  {
    for (Fighter defender: defenders)
      if (!((Creature) defender).isFainted())
        return false;

    return true;
  }
}
