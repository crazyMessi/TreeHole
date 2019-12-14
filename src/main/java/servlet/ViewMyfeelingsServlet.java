package servlet;

import dao.FeelingDao;
import entities.Feeling;
import entities.MyToken;
import entities.OutPutInformation;
import entities.Userinformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 19892
 */
@WebServlet(urlPatterns = "/viewMyfeelingsServlet")
public class ViewMyfeelingsServlet extends BaseServlet {
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
            String timeLimit=request.getParameter("timeLimit");
            int tL=Integer.parseInt(timeLimit);
            FeelingDao feelingDao=new FeelingDao();
            //??应设置返回的list的长度
            //??重复请求时，返回的值可不能重复
            List<Feeling> feelingList=feelingDao.getFeelingList(tL,userinformation);
            if (feelingList!=null){
                outPutInformation.setData(feelingList);
            }else {
                outPutInformation.setMessage("心情列表为空");
            }
        }
        //输出
        Out out=new Out();
        out.out(outPutInformation,request,response);
    }
}
