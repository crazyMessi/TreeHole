package entities;


/**
 * @author 19892
 */
public class Comment {

  private long id;
  private long commentId;
  private String comContent;
  private java.sql.Timestamp comUpdateTime;
  private String userId;
  private long commentCount;
  private String type;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCommentId() {
    return commentId;
  }

  public void setCommentId(long commentId) {
    this.commentId = commentId;
  }


  public String getComContent() {
    return comContent;
  }

  public void setComContent(String comContent) {
    this.comContent = comContent;
  }


  public java.sql.Timestamp getComUpdateTime() {
    return comUpdateTime;
  }

  public void setComUpdateTime(java.sql.Timestamp comUpdateTime) {
    this.comUpdateTime = comUpdateTime;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public long getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(long commentCount) {
    this.commentCount = commentCount;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
