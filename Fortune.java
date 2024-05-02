public class Fortune implements Attributes {
    private final int armor;
    private final DiceType type;
    private final int hitModifier;
    private final int hpBonus;
    private final String name;

    public Fortune(String name, int hpBonus, int armor, int hitModifier) {
        this(name, hpBonus, armor, hitModifier, null);
    }

    public Fortune(String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.name = name;
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.type = type;
    }

    @Override
    public int getMaxHP() {
        return hpBonus;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    @Override
    public DiceType getDamageDie() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
     public String toString() {
        return String.format(
            "+======================+%n" +
            "|%-22s|%n" + // Name left-aligned
            "|HP Bonus: %12s|%n" + // HP right-aligned
            "|AC Bonus: %12s|%n" + // Armor right-aligned
            "|Hit Bonus: %11s|%n" + // Hit modifier right-aligned
            "|Damage Adj: %10s|%n" + // Damage modifier right-aligned
            "+======================+",
            getName(), // Attached to %-22s
            (getMaxHP() > 0 ? "+" : "") + getMaxHP(), // Attached to %12s
            (getArmor() > 0 ? "+" : "") + getArmor(), // Attached to %12s
            (getHitModifier() > 0 ? "+" : "") + getHitModifier(), // Attached to %11s
            getDamageDie() != null ? getDamageDie().toString() : "-" // Attached to %9s
        );
     }
        
     public static void main(String[] args) {
        Fortune ftn = new Fortune("Merlin Luck", 10, 5, 2, DiceType.D12);
        System.out.println(ftn.toString());
    }
    
    
}
