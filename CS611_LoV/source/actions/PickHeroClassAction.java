
package lmh.actions;

import java.util.*;
import lmh.creatures.*;
import lmh.interfaces.*;

// A class for picking and returning a class of a hero during party creation.
public class PickHeroClassAction extends PickItemAction
{
  public PickHeroClassAction()
  {
    super ("Pick hero class", new String[]{
           "The following classes are available: ",
           "Please pick a class for the next hero: " });
  }

  public void execute (Fighter attacker)
  {
    return;
  }

  // Takes a list of strings to be shown to the player to choose from then
  // creates a class out of that choice.
  public Class getHeroClass (String[] classNames)
  {
    Class heroClass = null;
    String className = pickItemFromList (new ArrayList<String> (Arrays.<String>asList(classNames)));
    if (className != null)
    {
      try
      {
        heroClass = Class.forName ("lmh.creatures." + className);
      }
      catch (Exception e)
      {
        System.out.println (e);
      }
    }
    return heroClass;
  }
}
