
package lmh;

import java.util.*;

// An abstraction for all games of all types. Defines an interface that must be
// implemented and provides defaults methods for all classes to use such as
// retrieving objects by the class and cpaturing player input.
public abstract class Game
{
  public abstract void play();

  public abstract void nextMove();

  public abstract void quit();

  public static String getInput (String[] allowedValues)
  {
    Scanner console = new Scanner (System.in);
    String input;

    while(true)
    {
      try
      {
        input = console.nextLine().trim().toUpperCase();

        if (Arrays.asList (allowedValues).contains("ANY_INT"))
        {
          Integer.parseInt(input);
          return input;
        }
        else if (Arrays.asList (allowedValues).contains (input))
          return input;

        throw new Exception();
      }
      catch (Exception e)
      {
        System.out.print("Invalid input. Try again: ");
      }
    }
  }

  // Picks from the given collection only the items of the given type and returns them.
  public static <T1, T2> ArrayList<T2> getByClass (Collection<T1> collection, Class<T2> itemClass)
  {
    ArrayList<T2> items = new ArrayList<T2>();

    for (T1 item: collection)
      if (itemClass.isInstance (item))
        items.add ((T2) item);

    return items;
  }
}
