package entities;


public class Feeling {

  private long feelingId;
  private String feelContent;
  private java.sql.Timestamp feUpdateTime;
  private String ifPrivate;
  private String userId;
  private long supportCount;
  private long commentCount;
  private String ifSupport="未点赞";



  public long getFeelingId() {
    return feelingId;
  }

  public void setFeelingId(long feelingId) {
    this.feelingId = feelingId;
  }


  public String getFeelContent() {
    return feelContent;
  }

  public void setFeelContent(String feelContent) {
    this.feelContent = feelContent;
  }


  public java.sql.Timestamp getFeUpdateTime() {
    return feUpdateTime;
  }

  public void setFeUpdateTime(java.sql.Timestamp feUpdateTime) {
    this.feUpdateTime = feUpdateTime;
  }


  public String getIfPrivate() {
    return ifPrivate;
  }

  public void setIfPrivate(String ifPrivate) {
    this.ifPrivate = ifPrivate;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public long getSupportCount() {
    return supportCount;
  }

  public void setSupportCount(long supportCount) {
    this.supportCount = supportCount;
  }


  public long getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(long commentCount) {
    this.commentCount = commentCount;
  }

  public String getIfSupport(){return this.ifSupport;}

  public void setIfSupport(String ifSupport){this.ifSupport=ifSupport;}
}
