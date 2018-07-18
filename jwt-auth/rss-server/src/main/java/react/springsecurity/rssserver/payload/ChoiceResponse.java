package react.springsecurity.rssserver.payload;

public class ChoiceResponse {
  private long id;
  private String text;
  private long voteCount;

  public ChoiceResponse() {
  }

  public ChoiceResponse(long id, String text, long voteCount) {
    this.id = id;
    this.text = text;
    this.voteCount = voteCount;
  }

  public long getId() {

    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public long getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(long voteCount) {
    this.voteCount = voteCount;
  }
}
