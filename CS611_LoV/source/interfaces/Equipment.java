
package lmh.interfaces;

// Equipment is a special type of a usable item that can be equipped.
public interface Equipment extends Usable
{
  void equip();

  boolean canBeEquipped (int condition);
}
