
package lmh.locations;

import lmh.*;
import lmh.items.*;

// A class that represents a market location on the map.
public class Market extends Location
{
  public Market()
  {
    super ("market", Game.getByClass (GameFiles.getInstance().getAllObjects(), Item.class));
  }
}
