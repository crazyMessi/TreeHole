package servlet;

import dao.FeelingDao;
import entities.MyToken;
import entities.OutPutInformation;
import entities.Userinformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/shareTodayServlet")
public class ShareTodayServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否已登陆,若登陆则获取用户信息
        request.setCharacterEncoding("UTF-8");
        System.out.println("请求头为："+request);
        Userinformation userinformation;
        OutPutInformation outPutInformation=new OutPutInformation();
        String ifPrivate,content;
        //检查登陆状态并获取用户信息
        MyToken myToken=ifLogin(request);
        if (myToken==null){
            outPutInformation.setMessage("用户登陆状态异常");
            outPutInformation.setCode(-1);
        }else{
            userinformation=getUserinformation(myToken);
            //获取内容
            try {
                ifPrivate=request.getParameter("ifPrivate");
                content=request.getParameter("content");
                System.out.println(ifPrivate);
                System.out.println(content);
                FeelingDao feelingDao=new FeelingDao();
                outPutInformation.setData(feelingDao.up(content,ifPrivate,userinformation));
            }
            //??try-catch的常用错误
            catch (Exception e){
                e.fillInStackTrace();
                outPutInformation.setMessage("获取内容失败！");
                outPutInformation.setCode(-1);
            }
        }
        //输出
        Out out=new Out();
        out.out(outPutInformation,request,response);
    }
}
