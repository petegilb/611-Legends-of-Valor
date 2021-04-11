
package lmh.interfaces;

// Representable a tradeable item, that is one that can be bought and sold.
public interface Tradeable
{
  void buy();

  void sell();

  boolean canBeBought (int gold);

  boolean canBeSold();
}
