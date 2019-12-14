package servlet;

import dao.CommentDao;
import entities.Comment;
import entities.MyToken;
import entities.OutPutInformation;
import entities.Userinformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/commentServlet")
public class CommentServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否已登陆,若登陆则获取用户信息
        request.setCharacterEncoding("UTF-8");
        Userinformation userinformation;
        OutPutInformation outPutInformation=new OutPutInformation();
        MyToken myToken=ifLogin(request);
        if (myToken==null){
            outPutInformation.setCode(-1);
            outPutInformation.setMessage("登陆状态异常");
        }else {
            Comment comment=null;
            userinformation=getUserinformation(myToken);
            long feelingId,sayingId;
            String comContent;
            boolean aim1=(request.getParameter("feelingId")!=null);
            boolean aim2=(request.getParameter("sayingId")!=null);
            comContent=request.getParameter("comContent");
            CommentDao commentDao=new CommentDao();
            if(aim1&&aim2){
                outPutInformation.setMessage("传入参数过多！");
                outPutInformation.setCode(-1);
            }else {
                if (!(aim1||aim2)){
                    outPutInformation.setMessage("请传入参数！");
                    outPutInformation.setCode(-1);
                }else if(aim1){
                    feelingId=Integer.parseInt(request.getParameter("feelingId"));
                    comment=(commentDao.comment("feeling",feelingId,comContent,userinformation));
                }
                else if (aim2){
                    sayingId=Integer.parseInt(request.getParameter("sayingId"));
                    comment=(commentDao.comment("saying",sayingId,comContent,userinformation));
                }
                if (comment==null){
                    outPutInformation.setCode(1);
                    outPutInformation.setMessage("评论对象不存在或已被删除！");
                }else {
                    outPutInformation.setData(comment);
                }
            }
        }
        Out out=new Out();
        out.out(outPutInformation,request,response);
    }
}
