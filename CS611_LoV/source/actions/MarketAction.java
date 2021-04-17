
package lmh.actions;

import java.util.*;
import lmh.*;
import lmh.interfaces.*;
import lmh.creatures.*;
import lmh.locations.*;

// Represents the action of trading when the player encounters a market on the map.
public class MarketAction extends LocationAction
{
  public MarketAction (ArrayList<Hero> heroes)
  {
    super (heroes);

    ArrayList<Action> actions = new ArrayList<Action>();
    actions.add (new BuyItemAction (new Market()));
    actions.add (new SellItemAction());
    this.actions = actions;
  }

  // Drives the action. Adds its own login to the parent's class by asking the
  // player if they want to visit this market.
  public void useLocation()
  {
    System.out.print ("\nWould you like to visit the market? ");
    String input = Game.getInput(new String[]{ "Y", "N", "YES", "NO" });

    if (input.startsWith("N"))
      return;

    super.useLocation();
  }
}
