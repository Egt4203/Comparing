public class MOB implements Attributes {
    protected int armor;
    protected int damage;
    protected DiceType damageDie;
    protected int hitModifier;
    protected int maxHP;
    private final String name;

    public MOB(String name, int hp, int armor, int hitModifier, DiceType damageDie) {
        this.name = name;
        this.maxHP = hp;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.damageDie = damageDie;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public DiceType getDamageDie() {
        return damageDie;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void addDamage(int damage) {
        this.damage += damage;
        if(this.damage < 0) {
            this.damage = 0;
        } else if (this.damage > maxHP) {
            this.damage = maxHP;
        }
        }

    public int getHP() {
        return maxHP - damage;
    }

    public void resetDamage() {
        this.damage = 0;
    }

    @Override
    public String toString() {
        return "+============================+\n" +
                String.format("| %-27s|%n", getName()) +
                "|                            |\n" +
                String.format("|         Health: %-10d |%n", getHP())  +
                String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
                "|                            |\n" +
                "+============================+";
    }

    public MOB copy() {
        return new MOB(this.name, this.maxHP, this.armor, this.hitModifier, this.damageDie);
    }    
    
    public static void main(String[] args) {
        MOB JohnCena = new MOB("John Cena", 100, 100, 100, DiceType.D20);
        System.out.println(JohnCena);
    }
}
    
