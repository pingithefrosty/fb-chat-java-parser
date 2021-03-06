import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadWrite {
  private static final String CSV_SEPARATOR = "|";

  public static void listAndReadFiles(String directoryName){
    File directory = new File(directoryName);
    File[] fList = directory.listFiles();
    for (File file : fList){
      if (file.isFile() && file.getName().matches(".*.html")){
        Thread thread = new Thread(file.getName());
        try {
          Path filePath = Paths.get(directoryName + "\\" + thread.getOriginalFileName());
          thread.setLinesAsStrings(Files.readAllLines(filePath));
          Main.threadList.add(thread);
        } catch (Exception e) {
          System.out.println("Could not read file");
        }
      }
    }
  }

  public static void writeFile(List<String> output, String path) {
    try {
      Path filePath = Paths.get(path);
      Files.write(filePath, output);
    } catch (Exception e) {
      System.out.println("Could not write file!");
    }
  }

  public static void writeToIndividualCSV(Thread thread, String path) {
    try {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
      for (Message message : thread.getMessagesInThread()) {
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
    } catch (Exception e) {
      System.out.println("Could not write file!");
    }
  }

  public static void writeToCombinedCSV(ArrayList<Thread> threadList, String path) {
    try {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
      StringBuffer header = new StringBuffer();
      ArrayList<String> columnHeaders = new ArrayList<>(Arrays.asList("Conversation","Group?","Converted","ID","Sender",
          "Year","Month","Day","Day of the week","Hour","Zone","Chars","Words","Message"));
      for (String columnHeader : columnHeaders) {
        header.append(columnHeader.trim().length() == 0 ? "" : columnHeader);
        header.append(CSV_SEPARATOR);
      }
      bw.write(header.toString());
      bw.newLine();
      for (Thread thread : threadList) {
        for (Message message : thread.getMessagesInThread()) {
          StringBuffer oneLine = new StringBuffer();
          ArrayList<String> columns = new ArrayList<>(Arrays.asList(thread.getConversationName(),
              thread.getGroupConversation(), thread.getLastUpdated(), message.getMessageId(), message.getUser(),
              message.getDateOutputYear(), message.getDateOutputMonth(), message.getDateOutputDay(),
              message.getDateOutputDayOfWeek(), message.getDateOutputHourMinute(), message.getDateOutputTimeZone(),
              message.getContentNumberOfCharacters(), message.getContentNumberOfWords(), message.getContent()));
          for (String column : columns) {
            oneLine.append(column.trim().length() == 0 ? "" : column);
            oneLine.append(CSV_SEPARATOR);
          }
          bw.write(oneLine.toString());
          bw.newLine();
        }
      }
      bw.flush();
      bw.close();
    } catch (Exception e) {
      System.out.println("Could not write file!");
    }
  }
}
