
package lmh.interfaces;

// Represents a object that can trade in tradeables. So far, it's only Hero.
public interface Trader
{
  void buyItem (Tradeable item);

  void sellItem (Tradeable item);
}
