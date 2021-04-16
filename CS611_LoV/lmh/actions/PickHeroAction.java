
package lmh.actions;

import java.util.*;
import lmh.creatures.*;
import lmh.interfaces.*;

// A class for picking a hero during party creation, inventory access and market
// encounter.
public class PickHeroAction extends PickItemAction
{
  public PickHeroAction()
  {
    super ("Pick item", new String[]{
           "These heroes are available: ",
           "Please pick a hero: " });
  }

  public void execute (Fighter attacker)
  {
    return;
  }

  // Uses a method from the parent class to pick a hero from a list of heroes.
  public Hero getHero (ArrayList<Hero> heroes)
  {
    return pickItemFromList (heroes);
  }
}
