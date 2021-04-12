
package lmh.actions;

import java.util.*;
import lmh.*;
import lmh.interfaces.*;

// A class that allows all inheriting actions to pick an item from a list.
// Actions that use this class are: pick hero/class/action actions, buy/sell
// item actions and use spell/potion/equipment actions.
public abstract class PickItemAction implements Action
{
  private String name;
  protected String[] messages;

  public PickItemAction()
  {
    this ("unknown action", new String[0]);
  }

  public PickItemAction (String name, String[] messages)
  {
    this.name = name;
    this.messages = messages;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void setMessages (String[] messages)
  {
    this.messages = messages;
  }

  public String[] getMessages()
  {
    return messages;
  }

  // Takes an arbitrary list of items and asks the player to pick one.
  // If 0 is chosen then returns null.
  public <T> T pickItemFromList (ArrayList<T> items)
  {
    System.out.println ("\n" + messages[MSG_TYPE_AVAIL_ITEMS]);
    List<String> allowedValues = new ArrayList<String>();

    for (T item: items)
    {
      int i = items.indexOf (item) + 1;
      System.out.println ("(" + i + ") " + item);
      allowedValues.add (String.valueOf(i));
    }

    System.out.println ("(0) Go back");
    allowedValues.add (String.valueOf(0));
    System.out.print ("\n" + messages[MSG_TYPE_PICK_ITEM]);
    int input = Integer.parseInt (Game.getInput (allowedValues.toArray (new String[0])));

    if (input == 0)
      return null;

    return items.get (input-1);
  }

  public String toString()
  {
    return name;
  }
}
