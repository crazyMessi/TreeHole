package servlet;

import dao.MusicDao;
import dao.SayingDao;
import entities.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/toTreeHoleServlet")
public class ToTreeHoleServlet extends BaseServlet {
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
            try {
                userinformation=getUserinformation(myToken);
                String type=request.getParameter("type");
                String tag=request.getParameter("tag");
                if(tag!=getTag(tag)) {
                    tag=getTag(tag);
                    outPutInformation.setCode(1);
                    outPutInformation.setMessage("该标签名不存在，已自动改为“其他”");
                }
                int ty=type.equals("1")?1:2;
                switch (ty){
                    case 1:
                        SayingDao sayingDao=new SayingDao();
                        List<Saying> sayingList=sayingDao.getSayingList(tag,userinformation);
                        //get函数不确定是否可用
                        if (sayingList!=null){
                            outPutInformation.setData(sayingList.get(0));
                        }else{
                            outPutInformation=outPutInformation.of(1);
                        }
                        break;
                    case 2:
                        MusicDao musicDao=new MusicDao();
                        List<Music>musicList=musicDao.getMusicList(tag);
                        //get函数不确定是否可用
                        if(musicList!=null){
                            outPutInformation.setData(musicList.get(0));
                        }else {
                            outPutInformation=outPutInformation.of(1);
                        }
                        break;
                    default:break;
                }
            }catch (Exception e){
                outPutInformation=outPutInformation.of(-1);
            }
        }
        //输出
        Out out=new Out();
        out.out(outPutInformation,request,response);
    }
}
