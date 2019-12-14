package entities;


public class Music {


  private String musicUrl;
  private String tag;
  private String musicName;
  private long musicId;
  private String imgUrl;


  public void Music(String musicUrl,String tag ,String musicName,long musicId,String imgUrl){
    this.musicUrl=musicUrl;
    this.imgUrl=imgUrl;
    this.musicName=musicName;
    this.tag=tag;
    this.musicId=musicId;
  }

  public String getMusicUrl() {
    return musicUrl;
  }

  public void setMusicUrl(String musicUrl) {
    this.musicUrl = musicUrl;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }


  public String getMusicName() {
    return musicName;
  }

  public void setMusicName(String musicName) {
    this.musicName = musicName;
  }


  public long getMusicId() {
    return musicId;
  }

  public void setMusicId(long musicId) {
    this.musicId = musicId;
  }


  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }


}
