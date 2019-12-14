package entities;


public class Saying {

  private long sayingId;
  private String tag;
  private String content;
  private long supportCount;
  private long commentCount;
  private String imgUrl;
  private String ifSupport;

  public String getIfSupport(){return this.ifSupport;}
  public void setIfSupport(String ifSupport){return;}

  public long getSayingId() {
    return sayingId;
  }

  public void setSayingId(long sayingId) {
    this.sayingId = sayingId;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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


  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

}
