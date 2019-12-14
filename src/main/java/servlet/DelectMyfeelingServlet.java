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

/**
 * @author 19892
 */
@WebServlet(urlPatterns = "/delectMyfeelingServlet")
public class DelectMyfeelingServlet extends BaseServlet {
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
            userinformation=getUserinformation(myToken);
            long feelingId=Integer.parseInt(request.getParameter("feelingId"));
            FeelingDao feelingDao=new FeelingDao();
            outPutInformation.setCode(feelingDao.delect(feelingId,userinformation));
            if (outPutInformation.getCode()!=1) {
                outPutInformation.setMessage("删除失败！");
                outPutInformation.setCode(-1);
            }
        }
        //输出
        Out out=new Out();
        out.out(outPutInformation,request,response);
    }
}
