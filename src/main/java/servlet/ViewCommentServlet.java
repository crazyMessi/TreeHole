package servlet;

import dao.CommentDao;
import entities.Comment;
import entities.MyToken;
import entities.OutPutInformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewCommentServlet")
public class ViewCommentServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否已登陆,若登陆则获取用户信息
        request.setCharacterEncoding("UTF-8");
        OutPutInformation outPutInformation = new OutPutInformation();
        MyToken myToken=ifLogin(request);
        if (myToken==null){
            outPutInformation.setCode(-1);
            outPutInformation.setMessage("登陆状态异常");
        } else {
            List<Comment> commentList = null;
            long feelingId, sayingId;
            boolean aim1 = (request.getParameter("feelingId") != null);
            boolean aim2 = (request.getParameter("sayingId") != null);
            CommentDao commentDao = new CommentDao();
            if (aim1 && aim2) {
                outPutInformation.setMessage("传入参数过多！");
                outPutInformation.setCode(-1);
            } else {
                if (!(aim1 || aim2)) {
                    outPutInformation.setMessage("请传入参数！");
                    outPutInformation.setCode(-1);
                } else if (aim1) {
                    feelingId = Integer.parseInt(request.getParameter("feelingId"));
                    commentList = (commentDao.getCommentList(feelingId, "feeling"));
                } else if (aim2) {
                    sayingId = Integer.parseInt(request.getParameter("sayingId"));
                    commentList = (commentDao.getCommentList(sayingId, "saying"));
                }
                if (commentList == null) {
                    outPutInformation.setCode(-1);
                    outPutInformation.setMessage("获取评论列表失败！");
                } else {
                    outPutInformation.setData(commentList);
                }
            }
            //输出
            Out out=new Out();
            out.out(outPutInformation,request,response);
        }

    }
}
