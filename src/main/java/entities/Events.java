package entities;


public class Events {

  private String userId;
  private String target;
  private String support;
  private long id;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }


  public String getSupport() {
    return support;
  }

  public void setSupport(String support) {
    this.support = support;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

}
