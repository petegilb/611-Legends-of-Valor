
package lmh.actions;

import java.util.*;
import lmh.actions.*;
import lmh.interfaces.*;

// A class that allows the player to pick an action from a list of actions.
public class PickActionAction extends PickItemAction
{
  public PickActionAction()
  {
    super ("Pick action", new String[]{
           "These actions are available: ",
           "Please pick an action: " });
  }

  public void execute (Fighter attacker)
  {
    return;
  }

  // Uses the parent's class method to select and return an action from a list.
  public Action getAction (ArrayList<Action> actions)
  {
    return pickItemFromList (actions);
  }
}
