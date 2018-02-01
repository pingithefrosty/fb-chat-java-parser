public class Message {
  private String user;
  private String date;
  private String content;

  public Message() {
  }

  public Message(String user, String date, String content) {
    this.user = user;
    this.date = date;
    this.content = content;
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

