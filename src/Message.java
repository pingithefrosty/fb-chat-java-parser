public class Message {
  private String conversation;
  private String user;
  private String date;
  private String dateOutputYear;
  private String dateOutputMonth;
  private String dateOutputDay;
  private String dateOutputDayOfWeek;
  private String dateOutputHourMinute;
  private String dateOutputTimeZone;
  private String content;
  private String contentNumberOfCharacters;
  private String contentNumberOfWords;
  private String messageId;

  public Message() {
    this.content = " ";
    this.messageId = String.valueOf(Main.count.incrementAndGet());
  }

  public String getContentNumberOfCharacters() {
    return contentNumberOfCharacters;
  }

  public void setContentNumberOfCharacters(int contentNumberOfCharacters) {
    this.contentNumberOfCharacters = String.valueOf(contentNumberOfCharacters);
  }

  public String getContentNumberOfWords() {
    return contentNumberOfWords;
  }

  public void setContentNumberOfWords(int contentNumberOfWords) {
    this.contentNumberOfWords = String.valueOf(contentNumberOfWords);
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
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

  public String getDateOutputYear() {
    return dateOutputYear;
  }

  public void setDateOutputYear(String dateOutputYear) {
    this.dateOutputYear = dateOutputYear;
  }

  public String getDateOutputMonth() {
    return dateOutputMonth;
  }

  public void setDateOutputMonth(String dateOutputMonth) {
    this.dateOutputMonth = dateOutputMonth;
  }

  public String getDateOutputDay() {
    return dateOutputDay;
  }

  public void setDateOutputDay(String dateOutputDay) {
    this.dateOutputDay = dateOutputDay;
  }

  public String getDateOutputDayOfWeek() {
    return dateOutputDayOfWeek;
  }

  public void setDateOutputDayOfWeek(String dateOutputDayOfWeek) {
    this.dateOutputDayOfWeek = dateOutputDayOfWeek;
  }

  public String getDateOutputHourMinute() {
    return dateOutputHourMinute;
  }

  public void setDateOutputHourMinute(String dateOutputHourMinute) {
    this.dateOutputHourMinute = dateOutputHourMinute;
  }

  public String getDateOutputTimeZone() {
    return dateOutputTimeZone;
  }

  public void setDateOutputTimeZone(String dateOutputTimeZone) {
    this.dateOutputTimeZone = dateOutputTimeZone;
  }
}

