public class Knight extends MOB {
    private Fortune activeFortune;
    protected int xp;
    protected final int id;

    public Knight(int id, String name, int hp, int armor, int hitModifier, DiceType damageDie, int xp) {
       super(name, hp, armor, hitModifier, damageDie);
       this.id = id;
       this.xp = xp;
    }

    @Override
    public int getArmor() {
        if (activeFortune != null) {
            return super.getArmor() + activeFortune.getArmor();
        }
        return super.getArmor();
    }

    @Override
    public int getMaxHP() {
        if (activeFortune != null) {
            return super.getMaxHP() + activeFortune.getMaxHP();
        }
        return super.getMaxHP();
    }

    @Override
    public DiceType getDamageDie() {
        if (activeFortune != null) {
            return activeFortune.getDamageDie();
        }
        return super.getDamageDie();
    }

    @Override
    public int getHitModifier() {
        if (activeFortune != null) {
        return super.getHitModifier() + activeFortune.getHitModifier();
   }
   return super.getHitModifier();
}

    public void addXP(int xp) {
        this.xp += xp;
    }

    public int getId() {
        return id;
    }

    public int getXP() {
        return xp;
    }

    public Fortune getActiveFortune() {
        return activeFortune;
    }

    public void setActiveFortune(Fortune activeFortune) {
        this.activeFortune = activeFortune;
    }

    @Override
    public String toString() {
    return String.format(
        "+============================+%n" +
        "| %-27s|%n" + // Name left-aligned
        "| id: %-23d|%n" + // id right-aligned
        "|                            |%n" + // Empty line
        "| Health: %-10dXP: %-5d|%n" + // Health and XP
        "| Power: %-6sArmor: %-7d|%n" + // Power and Armor
        "|                            |%n" + // Empty line
        "+============================+",
        getName(), // Attached to %-27s
        getId(), // Attached to %-23d
        getHP(), // Attached to %-10d
        getXP(), // Attached to %-7d
        getDamageDie().toString(), // Attached to %-6s
        getArmor() // Attached to %-4d
    );
}

    public String toCSV() {
        return String.format(
        "%s,%d,%d,%d,%s,%d", 
        getName(), getMaxHP(), getArmor(),
        getHitModifier(),getDamageDie().toString(),
        getXP()
        );
    }

    public static void main(String[] args) {
        Knight knight = new Knight(1, "Evan", 2, 0, 0, DiceType.D10, 4);
        System.out.println(knight);
    }
    
    
}
