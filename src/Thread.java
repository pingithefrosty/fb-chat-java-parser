import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Thread {
  private String originalFileName;
  private String conversationName;
  private String lastUpdated;
  private List<String> linesAsStrings;
  private ArrayList<String> participantsInThread;
  private ArrayList<Message> messagesInThread;

  public Thread(String originalFileName) {
    this.originalFileName = originalFileName;
    this.lastUpdated = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    participantsInThread = new ArrayList<>();
    messagesInThread = new ArrayList<>();
  }

  public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public String getConversationName() {
    return conversationName;
  }

  public void setConversationName(String conversationName) {
    this.conversationName = conversationName;
  }

  public List<String> getLinesAsStrings() {
    return linesAsStrings;
  }

  public void setLinesAsStrings(List<String> linesAsStrings) {
    this.linesAsStrings = linesAsStrings;
  }

  public ArrayList<String> getParticipantsInThread() {
    return participantsInThread;
  }

  public void setParticipantsInThread(ArrayList<String> participantsInThread) {
    this.participantsInThread = participantsInThread;
  }

  public ArrayList<Message> getMessagesInThread() {
    return messagesInThread;
  }

  public void setMessagesInThread(ArrayList<Message> messagesInThread) {
    this.messagesInThread = messagesInThread;
  }
}
