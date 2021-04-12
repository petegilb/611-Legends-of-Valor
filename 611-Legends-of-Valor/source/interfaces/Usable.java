
package lmh.interfaces;

// Represents a special type of an item that can be used.
public interface Usable
{
  void use();

  boolean canBeUsed (int level);

  boolean isEmpty();
}
