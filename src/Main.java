import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
  static List<String> lines = new ArrayList<>();
  static String myName = "Barnabas Kdr";
  static String othersName;

  public static void main(String[] args) {
    readFile("data/Katka.html");
    createMessageObjects();

    fieldIdentifier();
    writeFile(divideByHtmlBlockIntoArray(createStringFromList(lines)),"data/Katka2.html");
  }

  private static void othersNameGetter(List<String> wholeConversation) {
    othersName = wholeConversation.get(0).split("&nbsp;")[1];
    System.out.println(othersName);
  }

  private static int fieldIdentifier(String excrept) {
    if ((excrept.equals(othersName)) || excrept.equals(myName)) {
      return 0;
    } else if ((excrept.startsWith("Monday")) || (excrept.startsWith("Tuesday")) || (excrept.startsWith("Wednesday")) ||
        (excrept.startsWith("Thursday")) || (excrept.startsWith("Friday")) || (excrept.startsWith("Saturday")) ||
        (excrept.startsWith("Sunday"))) {
      return 1;
    } else {
      return 2;
    }
  }

  private static String[] createMessageObjects(List<String> longList) {
    for (int i = 0; i < longList.size(); i++) {
      
    }
    return null;
  }

  private static List<String> divideByHtmlBlockIntoArray(String result) {
    String[] dividedToTwo = result.split("<div class=\"thread\">");
    String onlyThread = dividedToTwo[1];
    String[] splitted = onlyThread.split("<(.*?)>", -1);
    List<String> splittedWithoutNull = new ArrayList<>();
    for (int i = 0; i < splitted.length; i++) {
      if (!splitted[i].isEmpty()) {
        splittedWithoutNull.add(splitted[i]);
      }
    }
    othersNameGetter(splittedWithoutNull);
    return splittedWithoutNull;
  }

  private static void readFile(String path) {
    try {
      Path filePath = Paths.get(path);
      lines = Files.readAllLines(filePath);
    } catch (Exception e) {
      System.out.println("Could not read file");
    }
  }

  private static void writeFile(List<String> output, String path) {
      try {
        Path filePath = Paths.get(path);
        Files.write(filePath, output);
      } catch (Exception e) {
        System.out.println("Could not write file!");
      }
    }

  private static String createStringFromList(List<String> lines) {
    String everyString = "";
    for (int i = 0; i < lines.size(); i++) {
      everyString += lines.get(i);
    }
    return everyString;
  }
}
