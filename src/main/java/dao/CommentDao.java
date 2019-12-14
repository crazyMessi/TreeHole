package dao;

import entities.Comment;
import entities.Feeling;
import entities.Saying;
import entities.Userinformation;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 19892
 */
public class CommentDao extends BaseDao<Comment> {
    //评论·
    public Comment comment(String type,long id, String comContent, Userinformation userinformation){
        String typeId=type+"Id";
        Timestamp updateTime =new Timestamp(System.currentTimeMillis());
        String insertCommentSql="INSERT INTO `comment` (id,comContent,comUpdateTime,userId,type,commentCount) VALUE (?,?,?,?,?,?)";
        String updateCommentCountSql="UPDATE`"+type+"`SET commentCount=? WHERE`"+typeId+"`=?";
        String getComment="SELECT * FROM `comment` WHERE id=? AND comUpdateTime=? AND type=?";
        String getCommentCount="SELECT * FROM `"+type+"` WHERE `"+typeId+"`=?";
        Comment comment;
        long commentCount;
        //根据所评论对象的id获得其评论数
        if ("saying".equals(type))
        {
            BaseDao<Saying> baseDao=new BaseDao();
            commentCount=baseDao.find(Saying.class,getCommentCount,id).getCommentCount()+1;
        }else {
            BaseDao<Feeling>baseDao=new BaseDao<>();
            commentCount=baseDao.find(Feeling.class,getCommentCount,id).getCommentCount()+1;
        }
        //提交评论到comment表格
        int code=update(insertCommentSql,id,comContent,updateTime,userinformation.getUserId(),type,commentCount);
        //如果成功 更新type表中的commentCount
        if (code!=-1){
            //更新"type"表中的commentCount
            update(updateCommentCountSql,commentCount,id);
            //根据所评论的对象id、更新时间、类型获得完整的comment对象；
            comment=find(Comment.class,getComment,id,updateTime,type);
            return comment;
        }else {
            return null;
        }
    }
    //获得评论列表
    public List<Comment> getCommentList(long id, String type){
        //获取评论对象的序号名称
        String sql="SELECT * FROM `comment` WHERE id=? AND `type`='"+type+"'";
        if (list(Comment.class,sql,id)!=null){
            return list(Comment.class,sql,id);
        }else
        {
            return null;
        }
    }
}
