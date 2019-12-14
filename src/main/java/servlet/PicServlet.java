package servlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * pic
 *
 * @author Richard-J
 * @description
 * @date 2019.11
 */

@WebServlet("/pic")
public class PicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String base64Data=request.getParameter("picture");
        String [] d = base64Data.split("base64,");
        if(d != null && d.length == 2){
            String dataPrix = d[0];//获取到的前缀
            String data = d[1];//获取到的图片内容
        }

    }
}
