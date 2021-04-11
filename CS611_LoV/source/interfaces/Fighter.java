
package lmh.interfaces;

// An interface that every creature that wants to participate in combat must
// implement. Contains main methods for making combat function.
public interface Fighter
{
  int calculateDamage();

  int calculateDefense();

  int calculateDodgeChance();

  void nextCombatRound (Fighter defender);

  void roundIncrease();

  void combatIncrease (int opponentLevel, int health);

  void levelIncrease();

  void combatDecrease (int health);

  void skillDecrease (Usable item);
}
