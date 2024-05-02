import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static String gamedata = "gamedata.csv";
    private static String saveData = "knights.csv";


    public Main() {}

    public static void main(String[] args) {
        processArgs(args);

        GameData data = new CSVGameData(gamedata, saveData);
        GameView view = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);

        controller.start();
    }

    private static void processArgs(String[] args) {
        for (String arg : args) {
            Scanner scanner = new Scanner(arg);
            scanner.useDelimiter("=");
            String next;

            while (scanner.hasNext()) {
                next = scanner.next();
                if (next.equals("--data")) {
                    gamedata = scanner.next();
                    next = scanner.next();
                }
                saveData = next;
            }
        }
    }
}