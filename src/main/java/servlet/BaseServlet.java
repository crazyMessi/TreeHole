package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.UserinformationDao;
import entities.MyToken;
import entities.Userinformation;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 19892
 */
public class BaseServlet extends HttpServlet {
    //获得输入流
    protected String getStringFromStream(HttpServletRequest req) {
        ServletInputStream is;
        try {
            is = req.getInputStream();
            int nRead = 1;
            int nTotalRead = 0;
            byte[] bytes = new byte[10240];
            while (nRead > 0) {
                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0) {
                    nTotalRead = nTotalRead + nRead;
                }
            }
            String str = new String(bytes, 0, nTotalRead, "utf-8");
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    //根据请求头 获得myToken对象（若未登录则返回null）
    private MyToken getToken(HttpServletRequest request){
        String token=getStringFromStream(request);
        if(token==null||token.equals("")){
            assert request.getHeader("token")!=null;
            token=request.getHeader("token");
        }
        JSONObject jsonObject=JSON.parseObject(token);
        if(jsonObject!=null){
            token=jsonObject.getString("token");
            MyToken myToken=MyToken.valid(token);
            return myToken;
        }
        return null;
    }



     //判断是否登陆
    protected MyToken ifLogin(HttpServletRequest request){
        return getToken(request);
//        if (request.getSession().getAttribute("login") != null) {
//            login = (boolean) request.getSession().getAttribute("login");
//        }
//        Cookie cookie[]=request.getCookies();
//        if (cookie!=null){
//            for(int i=0;i<cookie.length;i++){
//                if("login".equals(cookie[i].getName())){
//                    login=(cookie[i].getValue().equals("true"));
//                    break;
//                }
//            }
//        }
        //getHeader或许也可（取决于前端）
    }
    //在登陆的状态下返回完整用户信息
    protected Userinformation getUserinformation(MyToken myToken){
        Userinformation userinformation;
            UserinformationDao userinformationDao=new UserinformationDao();
            userinformation=userinformationDao.trackUserId(myToken.getUserId());
            return userinformation;
    }
    protected String getTag(String tag){
        if("love".equals(tag)|| "family".equals(tag)|| "study".equals(tag)) {
            return tag;
        } else {
            return "other";
        }
    }
}