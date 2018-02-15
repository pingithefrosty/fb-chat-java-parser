import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class Main {
  private static final String MY_NAME = "Barnabas Kdr";
  public static final AtomicInteger count = new AtomicInteger(0);
  public static ArrayList<Thread> threadList = new ArrayList<>();

  public static void main(String[] args) throws ParseException {
    ReadWrite.listAndReadFiles("data/FBall");
    for (Thread aThread: threadList) {
      divideByHtmlBlockIntoArray(aThread);
      participantGetter(aThread);
      createMessageObjects(aThread);
      reformatMessageDate(aThread);
    }
    ReadWrite.writeToCombinedCSV(threadList, "data/combined_" + currentTime() + ".csv");
  }

  private static void reformatMessageDate(Thread thread) throws ParseException {
    for (int i = 0; i < thread.getMessagesInThread().size(); i++) {
      Message message = thread.getMessagesInThread().get(i);
      String[] dateSeparatedByComma = message.getDate().split(", ");
      message.setDateOutputDayOfWeek(dateSeparatedByComma[0]);
      String[] monthDay = dateSeparatedByComma[1].split(" ");
      message.setDateOutputMonth(monthDay[0]);
      message.setDateOutputDay(monthDay[1]);
      String[] rest = dateSeparatedByComma[2].split(" ");
      message.setDateOutputYear(rest[0]);
      message.setDateOutputTimeZone(rest[3]);
      SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
      SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mma");
      Date date = parseFormat.parse(rest[2]);
      message.setDateOutputHourMinute(displayFormat.format(date));
    }
  }

  private static void participantGetter(Thread thread) {
    List<String> threadLines = thread.getLinesAsStrings();
    thread.setConversationName(threadLines.get(0).replace("Conversation with ", ""));
    String[] othersPossibleName = threadLines.get(1).replace("Participants: ", "").split(", ");
    thread.getParticipantsInThread().addAll(Arrays.asList(othersPossibleName));
    thread.getParticipantsInThread().add(MY_NAME);
    if (thread.getParticipantsInThread().size() > 2) {
      thread.setGroupConversation("1");
    }
    System.out.println(thread.getOriginalFileName() + "   " + thread.getConversationName());
  }

  private static int fieldIdentifier(Thread thread, String excrept) {
    ArrayList<String> days = new ArrayList<>(Arrays.asList("Monday","Tuesday","Wednesday","Thursday",
        "Friday","Saturday","Sunday"));
    if (thread.getParticipantsInThread().contains(excrept)) {
      return 0;
    } else if (days.contains(excrept)) {
      return 1;
    } else {
      return 2;
    }
  }

  private static void createMessageObjects(Thread thread) {
    List<String> longList = thread.getLinesAsStrings();
    count.set(0);
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
        message.setContentNumberOfCharacters(allContent.length());
        String trimmed = allContent.trim();
        message.setContentNumberOfWords(trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length);
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

  private static String currentTime() {
    return new SimpleDateFormat("yyyy.MM.dd_hh.mm").format(Calendar.getInstance().getTime());
  }
}