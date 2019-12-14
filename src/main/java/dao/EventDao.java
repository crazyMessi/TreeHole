package dao;

import entities.Events;

public class EventDao extends BaseDao<Events> {
    public int trackEvent(String ifSupport,long id,String userId,String target)
    {
        String sql1="SELECT * FROM events WHERE id=? AND userId=? AND target=?";
        String sql2="UPDATE events SET support=? WHERE id=? AND userId=? AND target=?";
        String sql3="INSERT INTO events (support,id,userId,target) VALUE (?,?,?,?)";
        int code;
        if ("1".equals(ifSupport)){
            ifSupport="点赞";
        }else{
            ifSupport="未点赞";
        }
//        获得用户最后一次的点赞操作
        if (list(Events.class,sql1,id,userId,target)!=null){
//            若不为空 检查是否与当前操作一致 若一致则取消返回-1 取消当前操作
            Events events=list(Events.class,sql1,id,userId,target).get(0);
            if (events.getSupport().equals(ifSupport)){
                code=-1;
            }else {
//                若不一致 则以当前操作为准
                code=update(sql2,ifSupport,id,userId,target);
            }
        }else {
//            若为第一次点赞 则直接设为“点赞” （防止“踩”的）现象
            code=update(sql3,"点赞",id,userId,target);

        }
        return code;
    }
    public String getEvent(long id,String userId,String target){
        String sql="SELECT * FROM events WHERE id=? AND userId=? AND target = ?";
        if (list(Events.class,sql,id,userId,target)!=null) {
            return list(Events.class,sql,id,userId,target).get(0).getSupport();
        }else {
            return "未点赞";
        }
    }
}
