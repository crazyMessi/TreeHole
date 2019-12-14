package dao;

import entities.Userinformation;

/**
 * @author 19892
 */
public class UserinformationDao extends BaseDao<Userinformation>{
    //储存登陆登陆过的userId（部分表格的外键包含userId）
    public Userinformation trackUserId(String userId){
        String sql="SELECT * FROM `userinformation` WHERE `userId`=?";
        //userPhoto为默认值
        String sql1="INSERT INTO `userinformation` SET `userId`=?,`userPhoto`=?";
        Userinformation userinformation=find(Userinformation.class,sql,userId);
        if (userinformation==null){
            update(sql1,userId,"s/TreeHoleSource/picture/userPhoto/png/studentOnline.png");
            return find(Userinformation.class,sql,userId);
        }
        return userinformation;
    }
    //更换用户头像
    public int changeUserPhoto(String url,Userinformation userinformation){
        String sql="UPDATE`userinformation`SET `userPhoto`=? WHERE`userId`=?";
        url=url.substring(25);
        return update(sql,url,userinformation.getUserId());
    }
}
