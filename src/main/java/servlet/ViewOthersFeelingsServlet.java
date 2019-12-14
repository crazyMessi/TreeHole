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

@WebServlet(urlPatterns= "/viewOthersFeelingsServlet")
public class ViewOthersFeelingsServlet extends BaseServlet {
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
            long page=1,limit=2;
            try {
                page=Integer.parseInt(request.getParameter("page"));
                limit=Integer.parseInt(request.getParameter("limit"));
            }catch (Exception e){
                outPutInformation.setCode(-1);
                outPutInformation.setMessage("请传入参数！");
            }
            if(page<0||limit>5||limit<1){
                page=1;
                limit=2;
            }
            userinformation=getUserinformation(myToken);
            FeelingDao feelingDao=new FeelingDao();
            List<Feeling>feelingList=feelingDao.getFeelingList(page,limit,userinformation);
            boolean t=(feelingList!=null);
            if(t) {
                outPutInformation.setData(feelingList);
            }else {
                outPutInformation.setMessage("目前尚无人发言");
            }
        }
        //输出
        Out out=new Out();
        out.out(outPutInformation,request,response);
    }
}
