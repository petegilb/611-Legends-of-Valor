
package lmh.items;

// The parent class for all spells. Holds their common state.
public class Spell extends UsableItem
{
  protected int damageRange;
  protected int damage;
  protected int manaPrice;
  protected int[] coefs;

  public Spell()
  {
    this ("unknown spell", 0, 0, "Spell", 1, 0, 0, new int[]{ 0, 0, 0 });
  }

  public Spell (String name, int goldPrice, int minLevel, String type,
                int damageRange, int damage, int manaPrice, int[] coefs)
  {
    super (name, goldPrice, minLevel, type, -1);

    this.damageRange = damageRange;
    this.damage = damage;
    this.manaPrice = manaPrice;
    this.coefs = coefs;
  }

  public int getManaPrice ()
  {
    return manaPrice;
  }

  public int getDamage ()
  {
    return damage;
  }

  public int[] getCoefs ()
  {
    return coefs;
  }

  public String toString()
  {
    return super.toString() + ", Mana required to cast: " + manaPrice +
           ", Damage: " + damage + ", Damage reduction: " + coefs[0] +
           "%, Defense reduction: " + coefs[1] + "%, Dodge chance reduction: " +
           coefs[2] + "%";
  }
}
