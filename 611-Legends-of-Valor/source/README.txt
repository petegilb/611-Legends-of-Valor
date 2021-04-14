
package lmh
  Play.java                         // Driver class. Starts the game.
  Map.java                          // Map of the game class.
  LMHGame.java                      // LMH game class with specifics pertinent to LMH.
  GameFiles.java                    // Class for loading game files. Uses an inner class for file handling.
  AdventureGame.java                // Abstracts an adventure game. Parent to the LMH game class.
  Game.java                         // Abstracts any type of game and is parent to all games.
  Cell.java                         // Class that represents one cell on the map.

package lmh.locations
  Combat.java                       // Combat location. Runs when there is combat.
  Inventory.java                    // Inventory location. Holds items for each hero.
  Market.java                       // Market class for trading with each hero.
  Location.java                     // Abstracts location and serves as parent to all locations in the game.

package lmh.actions
  LocationAction.java               // Parent abstract class for all location (market, inventory) actions.
  UseItemAction.java                // Class that abstracts all actions that use items (drink poiton, buy/sell/equip item).
  CombatAction.java                 // Parent class to all combat (cast spell, attack) actions and holds their state.
  MarketAction.java                 // Class that handles market trading when player wants to visit the market.
  BuyItemAction.java                // Class that handles the action of buying an item at a market.
  PickItemAction.java               // Class that handles the action picking an item from a list (pick hero/action/class).
  AttackAction.java                 // Class that handles basic attack.
  EquipItemAction.java              // Class that handles equipment change (change weapon/armor).
  CastSpellAction.java              // Class that handles spell casting during combat.
  SellItemAction.java               // Class that handles selling an item at a market.
  PickActionAction.java             // Class that handles picking an action from a list of actions.
  PickHeroClassAction.java          // Class that handles picking the hero class during party creation.
  InventoryAction.java              // Class that handles working with inventory when player hits "I"
  DrinkPotionAction.java            // Class that handles drinking a potion.
  PickHeroAction.java               // Class that handles picking a hero during party creation.

package lmh.items
  IceSpell.java                     // Class that represents an ice spell.
  Potion.java                       // Class that represents a potion.
  Spell.java                        // Class that abstracts a spell and is parent to all spells.
  UsableItem.java                   // Class that represents an item that can be used (potion/spell/equipment).
  FireSpell.java                    // Class that represents a fire spell.
  LightningSpell.java               // Class that represents a lightning spell.
  Item.java                         // Class that abstracts an item and serves as parent to all items.
  Armor.java                        // Class that represents armor.
  Weapon.java                       // Class that represents weapons.

package lmh.creatures
  Hero.java                         // Class that represents an abstract hero (warrior/paladin/sorcerer).
  Creature.java                     // Class that represents a creature (hero/monster/unicorn).
  Warrior.java                      // Class that represents a warrior class hero.
  Paladin.java                      // Class that represents a paladin class hero.
  Sorcerer.java                     // Class that represents a sorcerer class hero.
  Spirit.java                       // Class that represents a spirit class monster.
  Monster.java                      // Class that represents an abstract monster (spirit/exoskeleton/dragon).
  Dragon.java                       // Class that represents a dragon class monster.
  Exoskeleton.java                  // Class that represents an exoskeleton class monster.

package lmh.interfaces
  Equipment.java                    // An interface for all equipment actions.
  Drawable.java                     // An interface for all drawable objects.
  Tradeable.java                    // An interface for all actions during trading.
  User.java                         // An interface for all actions a user of usables can do.
  Fighter.java                      // An interface for all actions a fighter can do.
  Usable.java                       // An interface for all usable objects.
  Trader.java                       // An interface for all actions a trader can do.
  Action.java                       // An interface that all actions must implement.
  TextColors.java                   // An interface that holds color codes (all two of them).


To compile:
  Extract the files into one directory.
  The following structure needs to be preserved:

  |-CS611_LMH
            |-GameFiles
            |-source

  Compile from a clean slate (UNIX terminal commands):
    cd CS611_LMH
    find . -name "*.class" -type f -delete
    find . -name "*.java" > sources.txt
    javac -d classes -cp classes @sources.txt

  The commands above will delete all .class files under CS611_LMH directory,
  then compile all .java files from a source file.

  After compilation a new directory "classes" will be created under CS611_LMH.
  Then use the following command to run:
    java -cp classes lmh.Play


Game behavior:
  - Potions file has been tweaked and new potions added.
  - Increased hero weapon damage.
  - Increased monster dodge chance.
  - All calculations use integer division with loss of precision.
  - Picking "change weapon/armor", "cast spell", or "use potion" during combat
    while not having anything in the inventory that can be equipped, cast, or used
    counts as a valid move and forfeits player's turn. This is by design
  - Damage goes to defense first. If damage is greater than defense, then the
    defender will lose health. If damage is less than monster defense or armor
    reduction than damage will be deflected
  - Heroes already picked during party creation are removed from choices. Duplicate
    heroes in the party are not possible.
  - Monsters are not removed during spawning. Duplicate monsters during combat
    are possible.

Enjoy the game! Have fun! :-)
