package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.UserinformationDao;
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
@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否已登陆,若登陆则获取用户信息
        MyToken myToken;
        Out out=new Out();
        UserinformationDao userinformationDao=new UserinformationDao();
        request.setCharacterEncoding("UTF-8");
        System.out.println("请求头为："+request+"\n");
        Userinformation userinformation=new Userinformation();
        OutPutInformation outPutInformation=new OutPutInformation();
        myToken=ifLogin(request);
        if(myToken!=null){
            outPutInformation.setMessage("用户已登陆");
            outPutInformation.setCode(1);
            outPutInformation.setData(getUserinformation(myToken));
            //检验是否真的登陆了
            if (outPutInformation.getData()==null){
                outPutInformation.setCode(-3);
                outPutInformation.setMessage("错误");
            }
        }else {
            //若未登录,则
            try {
                userinformation.setUserId(request.getParameter("userId"));
                userinformation.setUserPassword(request.getParameter("password"));
            }catch (Exception e){
                outPutInformation=outPutInformation.of(-1);
                out.out(outPutInformation,request,response);
                return;
            }
            Crawler crawler=new Crawler("","");
            outPutInformation.setCode(crawler.getCode(userinformation.getUserId(),userinformation.getUserPassword()));
            if (outPutInformation.getCode()!=0){
                outPutInformation=outPutInformation.of(-2);
            }else{
                userinformation=userinformationDao.trackUserId(userinformation.getUserId());
                if (userinformation==null){
                    outPutInformation=outPutInformation.of(-3);
                    out.out(outPutInformation,request,response);
                    return;
                }
                // generate a token
                // add to response body
//                request.getSession().setAttribute("login", true);
//                request.getSession().setAttribute("userId",userinformation.getUserId());
//                request.getSession().setAttribute("userPhoto",userinformation.getUserPhoto());
                //尝试使用cookies
//                Cookie c=new Cookie("login","true");
//                Cookie c1=new Cookie("userId",)
//                c.setMaxAge(24*60*60);
//                response.addCookie(c);
                //若登陆，则返回一个myToken对象给前端（而不是从请求头中获得token，，）
                myToken=new MyToken(userinformation.getUserId(),System.currentTimeMillis()+1000*3600*2);
                JSONObject object = JSON.parseObject(JSON.toJSONString(userinformation));
                object.put("token",myToken.generate());
                outPutInformation.setData(object);
            }
        }
        //输出
        out.out(outPutInformation,request,response);
        return;
    }
}


