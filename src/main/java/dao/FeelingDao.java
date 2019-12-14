package dao;

import entities.Feeling;
import entities.Userinformation;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 19892
 */
public class FeelingDao extends BaseDao<Feeling> {
    //删除心情记录
    EventDao eventDao=new EventDao();
    public int delect(long feelingId,Userinformation userinformation){
        int code;
        String sql="DELETE FROM feeling WHERE feelingId = ? AND userId = ?";
        code=update(sql,feelingId,userinformation.getUserId());
        return code;
    }
    //分享今日心情
    public Feeling up(String feelContent, String ifPrivate, Userinformation userinformation){
        int code=0;
        String userId=userinformation.getUserId();
        Timestamp updateTime=new Timestamp(System.currentTimeMillis());
        String sql="INSERT INTO feeling (feelContent,feUpdateTime,ifPrivate,userId,supportCount,commentCount) VALUE (?,?,?,?,?,?)";
        code=update(sql,feelContent,updateTime,ifPrivate,userId,0,0);
        if (code!=-1){
            Feeling feeling=find(Feeling.class,"SELECT * FROM feeling WHERE feUpdateTime = ? AND userId = ?",updateTime,userId);
            return feeling;
        }else {
            return null;
        }
    }
    //点赞ta的心情
    public String support(String ifSupport,long feelingId){
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
        String sql = "UPDATE feeling SET supportCount = supportCount + (?) WHERE feelingId = ?";
        code = update(sql, act, feelingId);
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
    //根据时限查看我的心情
    public List<Feeling> getFeelingList(int timeLimit,Userinformation userinformation){
        String userId=userinformation.getUserId();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        long t=timestamp.getTime();
        for (int i=0;i<timeLimit;i++){
            t-=3600*1000*24;
        }
        timestamp=new Timestamp(t);
        //能否可以比较待定
        String sql = "SELECT * FROM feeling WHERE  feUpdateTime > ? AND userId = ? ORDER BY feUpdateTime DESC";
        List<Feeling>feelingList=this.list(Feeling.class,sql,timestamp,userId);
        if (feelingList!=null){
            //确定心情列表的点赞状态
            for (int i=0;i<feelingList.size();i++){
                feelingList.get(i).setIfSupport(eventDao.getEvent(feelingList.get(i).getFeelingId(),userId,"feeling"));
            }
        }
        return feelingList;
    }
    //根据page、limit看ta的心情
    public List<Feeling> getFeelingList(long page,long limit,Userinformation userinformation){
        String sql = "SELECT * FROM `feeling` WHERE `userId` <> ? AND `ifPrivate`=1 ORDER BY feUpdateTime DESC LIMIT "+page*limit+","+limit;
        List<Feeling>feelingList=list(Feeling.class,sql,userinformation.getUserId());
        if (feelingList!=null){
            //确定心情列表的点赞状态
            for (int i=0;i<feelingList.size();i++){
                feelingList.get(i).setIfSupport(eventDao.getEvent(feelingList.get(i).getFeelingId(),userinformation.getUserId(),"feeling"));
            }
        }
        return feelingList;
    }
}



