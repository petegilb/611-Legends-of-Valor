
package lmh.actions;

import java.util.*;
import lmh.interfaces.*;
import lmh.creatures.*;

// Represents an action of opening the inventory when the player presses "I".
public class InventoryAction extends LocationAction
{
  public InventoryAction (ArrayList<Hero> heroes)
  {
    super (heroes);

    ArrayList<Action> actions = new ArrayList<Action>();
    actions.add (new DrinkPotionAction());
    actions.add (new EquipItemAction());
    this.actions = actions;
  }
}
