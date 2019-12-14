package dao;

import entities.Saying;
import entities.Userinformation;

import java.util.List;

/**
 * @author 19892
 */
//推普适性的话
public class SayingDao extends BaseDao<Saying> {
   EventDao eventDao=new EventDao();
   public List<Saying> getSayingList(String tag, Userinformation userinformation)
   {
      String sql="SELECT * FROM `saying` WHERE `tag`=? ORDER BY RAND() LIMIT 1;";
      List<Saying>sayingList=list(Saying.class,sql,tag );
      if (sayingList!=null) {
          for (int i=0;i<sayingList.size();i++) {
             sayingList.get(i).setIfSupport(eventDao.getEvent(sayingList.get(i).getSayingId(),userinformation.getUserId(),"saying"));
          }
      }
      return sayingList;
   }
   //点赞
   public String support(String ifSupport,long sayingId) {
      int code ;
      String message;
      int act;
      if ("1".equals(ifSupport)) {
         act = 1;
         message = "已点赞";
      } else if ("2".equals(ifSupport)) {
         act = -1;
         message = "未点赞";
      } else {
         return "错误操作";
      }
      String sql = "UPDATE saying SET supportCount = supportCount + (?) WHERE sayingId = ?";
      code = update(sql, act, sayingId);
      if (code != -1) {
         if (code==0){
            return "操作对象不存在";
         }else {
            return message;
         }
      } else {
         return "操作失败！";
      }
   }
   //添加saying
   public Saying addSaying(String url,String tag,String content){
      Saying saying;
      String sql="INSERT INTO `saying` (imgUrl,tag,content,supportCount,commentCount) VALUES (?,?,?,?,?)";
      String findSql1="SELECT * FROM `saying` WHERE imgUrl=?";
      url=url.substring(25);
      int code=update(sql,url,tag,content,0,0);
      if(code==1){
         saying=find(Saying.class,findSql1,url);
      }else
      {
         return null;
      }
      return saying;
   }
}
