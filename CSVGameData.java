import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class CSVGameData extends GameData {

    public CSVGameData(String gamedata, String saveData) {
        super();
        loadGameData(gamedata);
        loadSaveData(saveData);
    }

    private Knight parseKnight(int idCount, Scanner line) {
        line.useDelimiter(",");

        return new Knight(
                idCount,
                line.next().trim(),
                line.nextInt(),
                line.nextInt(),
                line.nextInt(),
                DiceType.typeOf(line.next().trim()),
                line.nextInt()
        );
    }

    private MOB parseMOB(Scanner line) {
        line.useDelimiter(",");

        return new MOB(
                line.next().trim(),
                line.nextInt(),
                line.nextInt(),
                line.nextInt(),
                DiceType.typeOf(line.next().trim())
        );
    }

    private Fortune parseFortune(Scanner line) {
        line.useDelimiter(",");

        return new Fortune(
                line.next().trim(),
                line.nextInt(),
                line.nextInt(),
                line.nextInt(),
                DiceType.typeOf(line.next().trim())
        );
    }


    private Scanner readFile(String fileName) {
        try {
            return new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.err.println(e);
        }
        return new Scanner("");
    }

    private void parseGameDataLine(Scanner line) {
        line.useDelimiter(",");

        String type = line.next();
        if (type.equals("MOB")) {
            monsters.add(parseMOB(line));
        }
        else if (type.equals("FORTUNE")) {
            fortunes.add(parseFortune(line));
        }
        else {
            System.err.println("Failed to parse line.");
        }
    }

    public void loadGameData(String gamedata) {
        Scanner file = readFile(gamedata);
        Scanner line;
        while (file.hasNext()) {
            line = new Scanner(file.nextLine());
            parseGameDataLine(line);
        }
    }

    public void loadSaveData(String saveData) {
        int idCount = 0;
        Scanner file = readFile(saveData);

        while (file.hasNext()) {
            ++idCount;
            knights.add(parseKnight(idCount, new Scanner(file.nextLine())));
        }
    }

    @Override
    public void save(String filename) {
        try (PrintWriter file = new PrintWriter(filename)) {
            for (Knight knight : knights) {
                file.println(knight.toCSV());
            }
            file.close();
        }
        catch (IOException e) {
            System.out.println("ERROR: Failed to save game.");
            System.err.println(e);
        }
    }

    }