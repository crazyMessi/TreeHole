package servlet;

import dao.EventDao;
import dao.FeelingDao;
import dao.SayingDao;
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
@WebServlet(urlPatterns = "/supportServlet")
public class SupportServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否已登陆,若登陆则获取用户信息
        request.setCharacterEncoding("UTF-8");
        Userinformation userinformation=new Userinformation();
        OutPutInformation outPutInformation=new OutPutInformation();
        MyToken myToken=ifLogin(request);
        if (myToken==null){
            outPutInformation.setCode(-1);
            outPutInformation.setMessage("登陆状态异常");
        }else {
            userinformation=getUserinformation(myToken);
            EventDao eventDao=new EventDao();
            String ifSupport,userId;
            long feelingId,sayingId;
            boolean aim1=((request.getParameter("feelingId"))!=null);
            boolean aim2=((request.getParameter("sayingId"))!=null);
            userId=userinformation.getUserId();
            ifSupport=request.getParameter("ifSupport");
            FeelingDao feelingDao=new FeelingDao();
            SayingDao sayingDao=new SayingDao();
            //检查传入参数的正确性、判断被点赞的类别
            if(aim1&&aim2){
                outPutInformation=outPutInformation.of(-1);
            }else {
                if (!(aim1||aim2)){
                    outPutInformation=outPutInformation.of(-1);
                }else if(aim1){
                    //记录、审核操作
                    feelingId=Integer.parseInt(request.getParameter("feelingId"));
                    outPutInformation.setCode(eventDao.trackEvent(ifSupport,feelingId,userId,"feeling"));
                    if (outPutInformation.getCode()!=-1) {
                        ifSupport=feelingDao.support(ifSupport,feelingId);
                        outPutInformation.setMessage("操作成功！");
                    } else {
                        outPutInformation.setMessage("操作失败，重复操作！");
                    }
                }else if (aim2){
                    //记录、审核操作
                    sayingId=Integer.parseInt(request.getParameter("SayingId"));
                    outPutInformation.setCode(eventDao.trackEvent(ifSupport,sayingId,userId,"saying"));
                    if (outPutInformation.getCode()!=-1) {
                        ifSupport=sayingDao.support(ifSupport,sayingId);
                        outPutInformation.setMessage("操作成功！");
                    } else {
                        outPutInformation.setMessage("操作失败，重复操作！");
                    }
                }
            }
        }
        //输出
        Out out=new Out();
        out.out(outPutInformation,request,response);
    }
}
