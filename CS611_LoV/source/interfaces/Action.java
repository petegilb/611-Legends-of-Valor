
package lmh.interfaces;

// Methods that all actions must implement and variables all actions can access.
public interface Action extends TextColors
{
  int MSG_TYPE_AVAIL_ITEMS = 0;
  int MSG_TYPE_PICK_ITEM = 1;
  int MSG_TYPE_NO_ITEMS = 2;

  void execute (Fighter attacker);
}
