import java.util.List;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private final Scanner in = new Scanner(System.in);

    public ConsoleView() {
    }

    @Override
    public void splashScreen() {
        System.out.println("Welcome to the Adventure Game!");
    }

    @Override
    public void endGame() {
        System.out.println("Thank you for playing!");
    }

    @Override
    public String displayMainMenu() {
        System.out.println("What would you like to do?");
        return in.nextLine();
    }

    @Override
    public void printHelp() {
        System.out.println(
            "Unsure what to do, here are some options:\n" +
            "            ls or list all  - listing the knights\n" +
            "            list active  - list the active knights knights only\n" +
            "            show name or id - show the knight details card\n" +
            "            set active name or id - set knight as active (note: only 4 knights can be active)\n" +
            "            remove active name or id - remove a knight from active status (heals knight)\n" +
            "            explore or adventure or quest - find random monsters to fight\n" +
            "            save filename - save the game to the file name (default: saveData.csv)\n" +
            "            exit or goodbye - to leave the game\n" +
            "\n" +
            "Game rules: You can have four active knights. As long as they are active, they won't heal, but they can gain XP by going on adventures.\n" +
            "When you make a knight inactive, they will heal. How many monsters can you defeat before, you have to heal?"
        );
        System.out.println();
    }

    @Override
    public void listKnights(List<Knight> knights) {
        if (knights.isEmpty()) {
            System.out.println("No knights to list");
        } else {
            knights.forEach(knight -> System.out.println(knight.getId() + ": " + knight.getName()));
        }
    }

    @Override
    public void knightNotFound() {
        System.out.println("Knight not found!");
    }

    @Override
    public void showKnight(Knight knight) {
        System.out.println(knight);
    }

    @Override
    public void setActiveFailed() {
        System.out.println("Unable to set active knight. Only four can be active at a time.");
    }

    @Override
    public void printBattleText(List<MOB> monsters, List<Knight> activeKnights) {
        System.out.println("Our heroes come across the following monsters. Prepare for battle!");
        System.out.println("Knights                     Foes");
        int minSize = Math.min(monsters.size(), activeKnights.size());
        for (int i = 0; i < minSize; i++) {
            System.out.printf("%-27s %s\n", activeKnights.get(i).getName(), monsters.get(i).getName());
        }
    }

    @Override
    public void printBattleText(MOB dead) {
        System.out.println(dead.getName() + " was defeated!");
    }

    @Override
    public void printFortunes(List<Knight> activeKnights) {
        System.out.println("For this quest, our knights drew the following fortunes!");
        activeKnights.forEach(knight -> {
            System.out.println(knight.getName() + " drew");
            System.out.println(knight.getActiveFortune());
        });
    }

    @Override
    public boolean checkContinue() {
        System.out.println("Would you like to continue on your quest (y/n)?");
        String response = in.nextLine();
        return response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes");
    }

    @Override
    public void printDefeated() {
        System.out.println("All active knights have been defeated!");
    }

    public static void main(String[] args) {

    }
}

