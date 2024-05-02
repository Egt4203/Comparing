import java.util.List;
import java.util.Random;

public class CombatEngine {
    private final GameData data; // The data for the game, passed in as part of the constructor
    private final DiceSet dice; // A DiceSet to be used for the Combat engine
    private final Random rnd; // Used in runCombat() to select who is fighting who
    private final GameView view; // The view used for the game, passed in as part of the constructor

    public CombatEngine(GameData data, GameView view) {
        this.data = data;
        this.view = view;
        this.dice = new DiceSet();
        this.rnd = new Random();
    }

    public void initialize() {
        for (Knight knight : data.getActiveKnights()) {
            knight.setActiveFortune(data.getRandomFortune());
        }
        view.printFortunes(data.getActiveKnights());
    }

    public void runCombat() {
        System.out.println("Not implemented");
    }

    private int doBattle(List<MOB> attackers, List<MOB> defenders) {
        return -1;
    }
    
    public void clear() {
        for (Knight knight : data.getKnights()) {
            knight.setActiveFortune(null);
        }
    }

    public static void main(String[] args) {
        // For testing
    }

}
