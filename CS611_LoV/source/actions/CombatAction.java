
package lmh.actions;

import java.util.*;
import lmh.locations.*;
import lmh.interfaces.*;

// Class that represents all combat actions (cast spell and attack).
// Holds the defender object that's needed by the combat actions to calculate
// their outcome.
public abstract class CombatAction extends UseItemAction
{
  protected Fighter defender;

  public CombatAction (String name, String[] messages, Fighter defender)
  {
    super (name, messages);
    this.defender = defender;
  }

  public void setDefender (Fighter defender)
  {
    this.defender = defender;
  }

  public Fighter getDefender()
  {
    return defender;
  }
}
