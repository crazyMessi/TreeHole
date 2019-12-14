package servlet;

import dao.MusicDao;
import entities.Music;
import entities.OutPutInformation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/addMusicServlet")
public class AddMusicServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Out out=new Out();
        OutPutInformation outPutInformation=new OutPutInformation();
        request.setCharacterEncoding("UTF-8");
        boolean check=true;
        FileFactory fileFactory=new FileFactory();
        String tag="other",keyword="",musicName="unnamed";
        //设置储存目录
        String baseUrl="/opt/apache-tomcat/webapps/TreeHoleSource/music_";
        try {
            keyword=request.getParameter("keyword");
            tag=request.getParameter("tag");
            baseUrl="/opt/apache-tomcat/webapps/TreeHoleSource/music_"+tag;
            musicName=request.getParameter("musicName");
        }catch (Exception e){
            outPutInformation=outPutInformation.of(-1);
            check=false;
        }
        if (check){
            //得到文件夹的url列表
            List<String> urlList=fileFactory.getUrl(request,baseUrl);
            String imgUrl="",musicUrl="";
            if (urlList!=null&&!urlList.isEmpty()){
                boolean imgCheck = false, musicCheck = false;
                for (String s : urlList) {
                    String url = s;
                    if (fileFactory.getFileType(url).equals("img")) {
                        imgUrl = url;
                        imgCheck = true;
                    }
                    if (fileFactory.getFileType(url).equals("music")) {
                        musicUrl = url;
                        musicCheck = true;
                    }
                }
                if (!musicCheck || !imgCheck) {
                    outPutInformation.setCode(-1);
                    outPutInformation.setMessage("文件参数不完整");
                    out.out(outPutInformation,request,response);
                    return;
                }
                if (musicCheck&imgCheck) {
                    MusicDao musicDao = new MusicDao();
                    Music music = musicDao.addMusic(musicUrl,imgUrl,tag,musicName);
                    if (music!=null) {
                        outPutInformation.setData(music);
                    } else {
                        outPutInformation=outPutInformation.of(-3);
                    }
                } else {
                    outPutInformation=outPutInformation.of(-2);
                }
            }else{
                outPutInformation.setCode(-1);
                outPutInformation.setMessage("获取文件失败！文件类型不存在！");
            }
        }
        out.out(outPutInformation,request,response);
    }

}
