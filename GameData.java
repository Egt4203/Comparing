import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GameData {
    protected final List<Knight> activeKnights; // List of the active knights, they are references not copies.
    protected final List<Fortune> fortunes;  // List of fortunes
    protected final List<Knight> knights; // List of all the knights available
    private static final int MAX_ACTIVE = 4; // the max number of active knights (defined by specifications to be 4)
    protected final List<MOB> monsters; // List of MOBs/Monsters
    private static final Random random = new Random(); //Random number Generator, used for grabbing random items for the structures

        public GameData() {
            this.fortunes = new ArrayList<>();
            this.monsters = new ArrayList<>();
            this.knights = new ArrayList<>();
            this.activeKnights = new ArrayList<>();
        }

        public List<Knight> getKnights() {
            return new ArrayList<>(knights);
        }

        public List<Knight> getActiveKnights() {
            return new ArrayList<>(activeKnights);
        }

        public Knight getActive(String nameOrId) {
            return findKnight(nameOrId, activeKnights);
        }

        public Knight getKnight(String nameOrId) {
            return findKnight(nameOrId, knights);
        }

        protected Knight findKnight(String nameOrId, List<Knight> list) {
            for (Knight knight : list) {
                if (String.valueOf(knight.getId()).equals(nameOrId) || knight.getName().toLowerCase().contains(nameOrId.toLowerCase())) {
                    return knight;
                }
            }
            return null;
        }        

        public boolean setActive(Knight kt) {
            if (activeKnights.size() < MAX_ACTIVE) {
                return activeKnights.add(kt);
            }
            return false;
        }

        public void removeActive(Knight kt) {
            activeKnights.remove(kt);
            kt.resetDamage();
        }

        public Fortune getRandomFortune() {
            return fortunes.isEmpty() ? null : fortunes.get(random.nextInt(fortunes.size()));
        }

        public List<MOB> getRandomMonsters() {
            return getRandomMonsters(activeKnights.size());
        }

        public List<MOB> getRandomMonsters(int number) {
            List<MOB> result = new ArrayList<>();
            for (int i = 0; i < number && i < monsters.size(); i++) {
                result.add(monsters.get(random.nextInt(monsters.size())).copy());
            }
            return result;
        }

        public abstract void save(String filename);

        public static void main(String[] args) {
            // Fill this later
    }
}
