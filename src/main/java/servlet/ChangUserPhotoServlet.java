package servlet;

import dao.UserinformationDao;
import entities.MyToken;
import entities.OutPutInformation;
import entities.Userinformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns ="/changUserPhotoServlet")
public class ChangUserPhotoServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否已登陆,若登陆则获取用户信息
        request.setCharacterEncoding("UTF-8");
        Userinformation userinformation;
        OutPutInformation outPutInformation=new OutPutInformation();
        boolean check=true;
        MyToken myToken=ifLogin(request);
        if (myToken==null){
            outPutInformation.setCode(-1);
            outPutInformation.setMessage("登陆状态异常");
            check=false;
        }
        if (check)
        {
            UserinformationDao userinformationDao=new UserinformationDao();
            userinformation=getUserinformation(myToken);
            String baseUrl="/opt/apache-tomcat/webapps/TreeHoleSource/picture/userPhoto";
            FileFactory fileFactory=new FileFactory();
            List<String> urlList=fileFactory.getUrl(request,baseUrl);
            check=(!urlList.isEmpty());
            if (check){
                A:
                for (String url : urlList) {
                    if ("img".equals(fileFactory.getFileType(url))) {
                        int code=userinformationDao.changeUserPhoto(url, userinformation) - 1;
                        if (code!=-1){
                            userinformation.setUserPhoto(url);
                            outPutInformation.setData(userinformation);
                        }else {
                            outPutInformation=outPutInformation.of(code);
                        }
                        break A;
                    }
                }
            }else {
                outPutInformation=outPutInformation.of(-3);
            }
        }
        Out out=new Out();
        out.out(outPutInformation,request,response);
    }
}
