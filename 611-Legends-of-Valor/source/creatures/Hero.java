
package lmh.creatures;

import java.util.*;

import lmh.Cell;
import lmh.interfaces.*;
import lmh.actions.*;
import lmh.items.*;
import lmh.locations.*;

import static lmh.interfaces.TextColors.ANSI_RED;
import static lmh.interfaces.TextColors.ANSI_RESET;

// Abstracts and holds state for all heroes in the game.
// Heroes are main users of all items in the game, so they implement many interfaces.
public abstract class Hero extends Creature implements Fighter, User, Trader, Drawable, Move
{
  protected int experience;
  protected int gold;
  protected int mana;
  protected int strength;
  protected int dexterity;
  protected int agility;
  protected int nextLevel;
  protected Inventory inventory;
  protected Equipment[] equipped;
  protected float[] coefs;

  public Hero()
  {
    this ("unknown hero", "Hero", 0, 0, 0, 0, 0, 0, new float[]{ 0.0f, 0.0f, 0.0f });
  }

  public Hero (String name, String type, int mana, int strength, int agility,
              int dexterity, int gold, int experience, float[] coefs)
  {
    super (name, type, 1);
    this.experience = experience;
    this.gold = gold;
    this.mana = mana;
    this.strength = strength;
    this.dexterity = dexterity;
    this.agility = agility;
    nextLevel = 10;
    inventory = new Inventory();
    equipped = new Equipment[2];
    equipped[0] = new Weapon (new String[]{ "Bare Hands", "0", "1", "100", "2" });
    equipped[1] = new Armor (new String[]{ "Bare Skin", "0", "1", "0" });
    this.coefs = coefs;
  }

  public void setExperience(int experience)
  {
    this.experience = experience;
  }

  public int getExperience()
  {
    return experience;
  }

  public void setGold(int gold)
  {
    this.gold = gold;
  }

  public int getGold()
  {
    return gold;
  }

  public void setMana(int mana)
  {
    this.mana = mana;
  }

  public int getMana()
  {
    return mana;
  }

  public void setStrength(int strength)
  {
    this.strength = strength;
  }

  public int getStrength()
  {
    return strength;
  }

  public void setDexterity(int dexterity)
  {
    this.dexterity = dexterity;
  }

  public int getDexterity()
  {
    return dexterity;
  }

  public void setAgility(int agility)
  {
    this.agility = agility;
  }

  public int getAgility()
  {
    return agility;
  }

  public void setNextLevel(int nextLevel)
  {
    this.nextLevel = nextLevel;
  }

  public int getNextLevel()
  {
    return nextLevel;
  }

  public Inventory getInventory()
  {
    return inventory;
  }

  public Equipment[] getEquipped()
  {
    return equipped;
  }

  public String toString()
  {
    if (isFainted())
      return name + " has fainted!";

    return super.toString() + ", Experience: " + experience + ", Gold: " + gold +
           ", Mana: " + mana + ", Strength: " + strength + ", Dexterity: " +
           dexterity + ", Agility: " + agility + ", Next Level: " + nextLevel;
  }

  public void draw()
  {
    System.out.println ("\nHero: " + this);
    System.out.println ("Equipped weapon: " + equipped[0]);
    System.out.println ("Equipped armor: " + equipped[1]);
    System.out.println ("Inventory: ");
    inventory.draw();
  }

  public void useItem (Usable item)
  {
    useItem (item, null);
  }

  // Main method for using a usable item. Fighter is needed only for casting a spell.
  public void useItem (Usable item, Fighter defender)
  {
    if (item instanceof lmh.items.Weapon)
    {
      Weapon weapon = (Weapon) item;
      if (((Weapon) equipped[0]).getName() != "Bare Hands")
        inventory.addItem ((Item) equipped[0]);
      equipped[0] = weapon;
      System.out.println ("\nHero " + name + " equipped " + weapon);
    }
    else if (item instanceof lmh.items.Armor)
    {
      Armor armor = (Armor) item;
      if (((Armor) equipped[1]).getName() != "Bare Skin")
        inventory.addItem ((Item) equipped[1]);
      equipped[1] = armor;
      System.out.println ("\nHero " + name + " equipped " + armor);
    }
    else if (item instanceof lmh.items.Potion)
    {
      Potion potion = (Potion) item;
      int[] attIncrease = potion.getAttIncrease();
      strength += attIncrease[0];
      dexterity += attIncrease[1];
      agility += attIncrease[2];
      health += attIncrease[3];
      mana += attIncrease[4];
      System.out.println ("Hero " + name + " drinks potion " +
                          potion.getName() + " and gets a boost!");
    }
    else if (item instanceof lmh.items.Spell)
    {
      Spell spell = (Spell) item;
      defender.skillDecrease (spell);
      System.out.println ("Hero " + name + " casts a spell on " +
                          ((Creature) defender).getName() + "!");
      Action action = new AttackAction (defender, calculateSpellDamage(spell));
      action.execute (this);
    }
    item.use();
    inventory.removeItem ((Item) item);
  }

  public void buyItem (Tradeable itm)
  {
    Item item = (Item) itm;
    gold -= item.getGoldPrice();
    inventory.addItem (item);
    System.out.println ("\nHero " + name + " bought item " + item.getName() +
                        " for " + item.getGoldPrice() + " gold");
    item.buy();
  }

  public void sellItem (Tradeable itm)
  {
    Item item = (Item) itm;
    gold = gold + item.getGoldPrice()/2;
    inventory.removeItem (item);
    System.out.println ("\nHero " + name + " sold item " + item.getName() +
                        " for " + item.getGoldPrice()/2 + " gold");
    item.sell();
  }

  public int calculateDamage()
  {
    return calculateWeaponDamage();
  }

  public int calculateWeaponDamage()
  {
    return (int) ((strength + ((Weapon) equipped[0]).getDamage()) * 0.5);
  }

  public int calculateSpellDamage (Spell spell)
  {
    return spell.getDamage() * (1 + dexterity / 10000);
  }

  public int calculateDefense()
  {
    return calculateArmorDefense();
  }

  public int calculateArmorDefense()
  {
    return ((Armor) equipped[1]).getDamRed();
  }

  public int calculateDodgeChance()
  {
    return (int) (agility * 0.02);
  }

  // This method is responsible for each round of combat when this hero is an
  // attacker. Builds an array then asks the player to choose what to do.
  public void nextCombatRound (Fighter defender)
  {
    draw();
    ArrayList<Action> actions = new ArrayList<Action>();
    actions.add (new AttackAction (defender, calculateWeaponDamage()));
    actions.add (new CastSpellAction (defender));
    actions.add (new DrinkPotionAction());
    actions.add (new EquipItemAction());
    System.out.println ("\nA hero only has time for one action. Choose wisely!\n");
    (new PickActionAction()).getAction (actions).execute (this);
  }

  // After each round this method is called to calculate the increase.
  public void roundIncrease()
  {
    int prevHealth = health;
    health = (int) (health * 1.1);
    System.out.println ("\n\n" + name + " restores " + (health - prevHealth) +
                        " health after a round of combat");

    int prevMana = mana;
    mana = (int) (mana * 1.1);
    System.out.println (name + " restores " + (mana - prevMana) +
                        " mana after a round of combat");
  }

  // After each combat this method is called to calculate the increase.
  // Since this is the only time in the game when new experience is received,
  // checks if a hero reached a new level, and, if so, proceeds to level
  // increase method.
  public void combatIncrease (int monLevel, int startingHealth)
  {
    if (isFainted())
    {
      health = startingHealth / 2;
      System.out.println (name + " restores half of their health after fainting in combat!");
    }
    else
    {
      gold += monLevel*100;
      System.out.println ("\n" + name + " receives " + (monLevel*100) +
                          " gold after defeating monsters in combat!");

      experience += 2;
      System.out.println (name + " gains 2 experience after defeating monsters in combat!");

      if (experience >= nextLevel)
        levelIncrease();
      else
      {
        health = level*100;
        System.out.println (name + " restores their full health after defeating monsters in combat!");
      }
    }
  }

  public void levelIncrease()
  {
    level += 1;
    System.out.println (name + " reaches level " + level + "!");

    int prevStr = strength;
    strength = (int) (strength * coefs[0]);
    System.out.println (name + " gains " + (strength - prevStr) + " strength!");

    int prevDex = dexterity;
    dexterity = (int) (dexterity * coefs[1]);
    System.out.println (name + " gains " + (dexterity - prevDex) + " dexterity!");

    int prevAgil = agility;
    agility = (int) (agility * coefs[2]);
    System.out.println (name + " gains " + (agility - prevAgil) + " agility!");

    int prevMana = mana;
    mana = (int) (mana * 1.1);
    System.out.println (name + " gains " + (mana - prevMana) + " mana!");

    int prevHP = health;
    health = level*100;
    System.out.println (name + " gains " + (health - prevHP) + " health!");

    int prevNL = nextLevel;
    nextLevel += level*10;
    System.out.println (name + " will reach a new level after gaining " +
                        (nextLevel - prevNL) + " experience");
  }

  // If lost in combat, processes the stat decrease.
  public void combatDecrease (int startingHealth)
  {
    gold = gold / 2;
    System.out.println ("\nHero " + name + " lost " + gold + " gold after being defeated in combat!");
    health = startingHealth / 2;
    System.out.println (name + " restores half of their health after being defeated in combat!");
  }

  // Processes the skill decrease if affected by any usable items (none so far exist).
  public void skillDecrease (Usable item)
  {
    return;
  }


  // TODO: 2021/4/11 logic of movement, teleport, and backToNexus
  public void makeMovement(int row, int col){

  };
  public int[] getCurrPosition(){
    return new int[]{-1, - 1};
  }

  public void teleport(){};
  public void backToNexus(){};
  public void printNeighborInfo(){}
}
