import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final String MY_NAME = "Barnabas Kdr";
  public static ArrayList<Thread> threadList = new ArrayList<>();


  public static void main(String[] args) {
    ReadWrite.listAndReadFiles("data");
    for (int i = 0; i < threadList.size(); i++) {
      divideByHtmlBlockIntoArray(threadList.get(i));
      participantGetter(threadList.get(i));
      createMessageObjects(threadList.get(i));
      ReadWrite.writeFile(threadList.get(i).getLinesAsStrings(),
          "data/" + threadList.get(i).getConversationName() + ".html");
      ReadWrite.writeToCSV(threadList.get(i), "data/" + threadList.get(i).getConversationName() + ".csv");
    }
  }

  private static void participantGetter(Thread thread) {
    List<String> threadLines = thread.getLinesAsStrings();
    thread.setConversationName(threadLines.get(0).replace("Conversation with ", ""));
    String[] othersPossibleName = threadLines.get(1).replace("Participants: ", "").split(", ");
    thread.getParticipantsInThread().addAll(Arrays.asList(othersPossibleName));
    thread.getParticipantsInThread().add(MY_NAME);
    System.out.println(thread.getParticipantsInThread());
    System.out.println(thread.getConversationName());
  }

  private static int fieldIdentifier(Thread thread, String excrept) {
    if (thread.getParticipantsInThread().contains(excrept)) {
      return 0;
    } else if ((excrept.startsWith("Monday")) || (excrept.startsWith("Tuesday")) || (excrept.startsWith("Wednesday")) ||
        (excrept.startsWith("Thursday")) || (excrept.startsWith("Friday")) || (excrept.startsWith("Saturday")) ||
        (excrept.startsWith("Sunday"))) {
      return 1;
    } else {
      return 2;
    }
  }

  private static void createMessageObjects(Thread thread) {
    List<String> longList = thread.getLinesAsStrings();
    for (int i = 0; i < longList.size(); i++) {
      if (fieldIdentifier(thread, longList.get(i)) == 0) {
        Message message = new Message();
        message.setUser(longList.get(i));
        message.setDate(longList.get(i + 1));
        String allContent = "";
        if (fieldIdentifier(thread, longList.get(i + 2)) == 2) {
          allContent += longList.get(i + 2);
        }
        if (fieldIdentifier(thread, longList.get(i+3)) == 2) {
          allContent += longList.get(i + 3);
        }
        message.setContent(allContent);
        thread.getMessagesInThread().add(message);
      }
    }
  }

  private static void divideByHtmlBlockIntoArray(Thread thread) {
    String result = createStringFromList(thread.getLinesAsStrings());
    String[] dividedToTwo = result.split("<div class=\"thread\">");
    String onlyThread = dividedToTwo[1];
    String[] splitted = onlyThread.split("<(.*?)>", -1);
    ArrayList<String> splittedWithoutNull = new ArrayList<>();
    for (int i = 0; i < splitted.length; i++) {
      if (!splitted[i].isEmpty()) {
        splittedWithoutNull.add(splitted[i]);
      }
    }
    splittedWithoutNull.addAll(Arrays.asList("", "", ""));
    thread.setLinesAsStrings(splittedWithoutNull);
  }

  private static String removeInvalidCharacters(String msg) {
    String[] htmlCharacter = {"&#034;","&#035;","&#036;","&#037;","&#038;","&#039;","&#040;", "&#041;",
        "&#042;","&#043;","&#044;","&#045;","&#046;","&#047;","&#058;","&#059;","&#060;","&#061;",
        "&#062;","&#063;","&#064;","&#091;","&#092;","&#093;","&#094;","&#095;","&#096;","&#125;"};
    String[] htmlReplacement = {"\"","#","$","%","&","'","(",")","*","+",",","-",
        ".","/",":",";","<","=",">","?","@","[","\\","]","^","_","`","~"};
    for (int i = 0; i < htmlCharacter.length; i++) {
      msg = Pattern.compile(htmlCharacter[i]).matcher(msg).replaceAll(htmlReplacement[i]);
    }
    return msg;
  }

  private static String createStringFromList(List<String> lines) {
    String everyString = "";
    for (int i = 0; i < lines.size(); i++) {
      everyString += lines.get(i);
    }
    return removeInvalidCharacters(everyString);
  }
}
