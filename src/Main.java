import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  private static List<String> lines = new ArrayList<>();
  private static final String MY_NAME = "Barnabas Kdr";
  private static final String CSV_SEPARATOR = "|";
  private static ArrayList<String> participantList = new ArrayList<>();
  private static ArrayList<Message> messageList = new ArrayList<>();

  public static void main(String[] args) {
    readFile("data/valina.html");
    ArrayList<String> writeTest = divideByHtmlBlockIntoArray(createStringFromList(lines));
    writeToCSV(messageList, "data/valina2.csv");
    writeFile(writeTest,"data/valina2.html");
  }

  private static void othersNameGetter(List<String> wholeConversation) {
    String othersPossibleName = wholeConversation.get(0).split("&nbsp;")[1];
    participantList.addAll(Arrays.asList(othersPossibleName.split(", ")));
    participantList.add(MY_NAME);
    System.out.println(participantList);
  }

  private static int fieldIdentifier(String excrept) {
    if (participantList.contains(excrept)) {
      return 0;
    } else if ((excrept.startsWith("Monday")) || (excrept.startsWith("Tuesday")) || (excrept.startsWith("Wednesday")) ||
        (excrept.startsWith("Thursday")) || (excrept.startsWith("Friday")) || (excrept.startsWith("Saturday")) ||
        (excrept.startsWith("Sunday"))) {
      return 1;
    } else {
      return 2;
    }
  }

  private static void createMessageObjects(List<String> longList) {
    for (int i = 0; i < longList.size(); i++) {
      if (fieldIdentifier(longList.get(i)) == 0) {
        Message message = new Message();
        message.setUser(longList.get(i));
        message.setDate(longList.get(i+1));
        String allContent = "";
        if (fieldIdentifier(longList.get(i+2)) == 2) {
          allContent += longList.get(i+2);
        }
        if (fieldIdentifier(longList.get(i+3)) == 2) {
          allContent += longList.get(i+3);
        }
        message.setContent(allContent);
        messageList.add(message);
      }
    }
  }

  private static ArrayList<String> divideByHtmlBlockIntoArray(String result) {
    String[] dividedToTwo = result.split("<div class=\"thread\">");
    String onlyThread = dividedToTwo[1];
    String[] splitted = onlyThread.split("<(.*?)>", -1);
    ArrayList<String> splittedWithoutNull = new ArrayList<>();
    for (int i = 0; i < splitted.length; i++) {
      if (!splitted[i].isEmpty()) {
        splittedWithoutNull.add(splitted[i]);
      }
    }
    othersNameGetter(splittedWithoutNull);
    splittedWithoutNull.addAll(Arrays.asList("", "", ""));
    createMessageObjects(splittedWithoutNull);
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

  private static String createStringFromList(List<String> lines) {
    String everyString = "";
    for (int i = 0; i < lines.size(); i++) {
      everyString += lines.get(i);
    }
    return everyString;
  }

  private static void writeFile(List<String> output, String path) {
    try {
      Path filePath = Paths.get(path);
      Files.write(filePath, output);
    } catch (Exception e) {
      System.out.println("Could not write file!");
    }
  }

  private static void writeToCSV(ArrayList<Message> messageList, String path) {
    try {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
      for (Message message : messageList) {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(message.getUser().trim().length() == 0? "" : message.getUser());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(message.getDate().trim().length() == 0? "" : message.getDate());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(message.getContent().trim().length() == 0? "" : message.getContent());
        oneLine.append(CSV_SEPARATOR);
        bw.write(oneLine.toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (Exception ignored) {}
  }
}
