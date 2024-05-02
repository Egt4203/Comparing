public class GameController {
    private final GameData data; // Game data passed into the constructor
    private final CombatEngine engine; // combat engine passed into the constructor
    private final GameView view; // game view passed into the constructor

    public GameController(GameData data, GameView view, CombatEngine engine) {
        this.data = data;
        this.view = view;
        this.engine = engine;
    }

    public void start() {
        view.splashScreen();
        String command = "";
        do {
            command = view.displayMainMenu();
        } while (processCommand(command));
        view.endGame();
    }
    

    protected boolean processCommand(String command) {
        if (command.contains("exit") || command.contains("bye")) {
            return false;
        } 
        else if (command.startsWith("ls") || command.startsWith("list all")) {
            view.listKnights(data.getKnights());
            System.out.println();
        } 
        else if (command.startsWith("list active")) {
            view.listKnights(data.getActiveKnights());
            System.out.println();
        } 
        else if (command.startsWith("show ")) {
            processShowKnight(command.substring(5));
        } 
        else if (command.startsWith("set active ")) {
            processSetActive(command.substring(11));
        } 
        else if (command.startsWith("remove ")) {
            processRemoveActive(command.substring(7));
        } 
        else if (command.startsWith("save ")) {
            String filename = command.substring(5).trim();
            if (filename.isEmpty()) {
                data.save("saveData.csv");
            } else {
                data.save(filename);
            }
        } else if (command.matches("explore|adventure|quest")) {
            engine.initialize();
            engine.runCombat();
            engine.clear();
        } else {
            view.printHelp();
        }
        return true;
    }
    

    private void processRemoveActive(String remove) {
        Knight knight = data.getActive(remove.trim());
        if (knight != null) {
            data.removeActive(knight);
        } else {
            view.knightNotFound();
        }
    }
    
    

    private void processSetActive(String active) {
        Knight knight = data.getKnight(active.trim());
        if (knight != null && data.setActive(knight)) {
        } else {
            view.setActiveFailed();
        }
    }

    private void processShowKnight(String nameOrId){
        Knight knight = data.getKnight(nameOrId.trim());
        if (knight != null) {
            view.showKnight(knight);
        } else {
            view.knightNotFound();
        }
    }


    public static void main(String[] args) {
    }
}
