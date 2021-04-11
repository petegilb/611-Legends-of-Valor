
package lmh.actions;

import java.util.*;
import lmh.interfaces.*;
import lmh.creatures.*;

// Abstracts a location (market, inventory) action and holds state that is common
// to all location actions.
public abstract class LocationAction implements Action
{
  ArrayList<Action> actions;
  ArrayList<Hero> heroes;

  public LocationAction()
  {
    this (new ArrayList<Hero>());
  }

  public LocationAction (ArrayList<Hero> heroes)
  {
    actions = new ArrayList<Action>();
    this.heroes = heroes;
  }

  public void setActions (ArrayList<Action> actions)
  {
    this.actions = actions;
  }

  public ArrayList<Action> getActions()
  {
    return actions;
  }

  public void setHeroes (ArrayList<Hero> heroes)
  {
    this.heroes = heroes;
  }

  public ArrayList<Hero> getHeroes()
  {
    return heroes;
  }

  // Drives the location actions. Loops asking the player for a hero, then loops
  // asking the player for an action, then executes the chosen action.
  public void useLocation()
  {
    while (true)
    {
      Hero hero = (new PickHeroAction()).getHero (heroes);

      if (hero == null)
        break;

      hero.draw();

      while (true)
      {
        Action action = (new PickActionAction()).getAction (actions);

        if (action == null)
          break;

        action.execute (hero);
      }
    }
  }

  // Implements the Action interface.
  public void execute (Fighter attacker)
  {
    useLocation();
  }
}
