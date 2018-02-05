public class Message {
  private String conversation;
  private String user;
  private String date;
  private String content;

  public Message() {
    this.content = " ";
  }

  public Message(String conversation, String user, String date, String content) {
    this.conversation = conversation;
    this.user = user;
    this.date = date;
    this.content = content;
  }

  public String getConversation() {
    return conversation;
  }

  public void setConversation(String conversation) {
    this.conversation = conversation;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}

