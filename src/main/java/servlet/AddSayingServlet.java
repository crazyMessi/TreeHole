package servlet;

import dao.SayingDao;
import entities.OutPutInformation;
import entities.Saying;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * @author 19892
 */
@WebServlet("/addSayingServlet")
public class AddSayingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutPutInformation outPutInformation=new OutPutInformation();
        request.setCharacterEncoding("UTF-8");

        boolean check=true;
        FileFactory fileFactory=new FileFactory();
        String tag="other",keyword="",content ="";
        String baseUrl="/opt/apache-tomcat/webapps/TreeHoleSource/saying";
        try {
            keyword=request.getParameter("keyword");
            tag=request.getParameter("tag");
            content=request.getParameter("content");
            baseUrl=baseUrl+"/"+tag;
        }catch (Exception e){
            outPutInformation.setMessage("传入参数错误！");
            outPutInformation.setCode(-2);
            check=false;
        }
        //当口令正确时执行
        if (check){
            Saying saying=null;
            List<String> urlList=fileFactory.getUrl(request,baseUrl);
            if (!urlList.isEmpty()){
                String imgUrl;
                for (String url : urlList) {
                    SayingDao sayingDao = new SayingDao();
                    //判断文件类型是否为图片类型
                    if ("img".equals(fileFactory.getFileType(url))) {
                        imgUrl=url;
                        saying = sayingDao.addSaying(imgUrl, tag, content);
                        break;
                    }
                }
                    if (saying == null) {
                        outPutInformation.setCode(-3);
                        outPutInformation.setMessage("数据库插入失败！");
                    }else {
                        outPutInformation.setData(saying);
                    }
            }else{
                outPutInformation.setCode(-2);
                outPutInformation.setMessage("文件类型错误！请联系后端增加类型或修改为指定类型");
            }
        }
        Out out=new Out();
        out.out(outPutInformation,request,response);
    }
}


