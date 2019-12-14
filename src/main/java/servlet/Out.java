package servlet;

import com.alibaba.fastjson.JSON;
import entities.OutPutInformation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Out extends BaseServlet {
    public void out(OutPutInformation outPutInformation, HttpServletRequest request,HttpServletResponse response) throws IOException {
        String output;
        //设置响应编码为utf-8
        response.setCharacterEncoding("UTF-8");
        //告知浏览器编码方式
        response.setContentType("application/json");
        //设置跨域请求头
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "*");

        //使用PrintWriter获得输出流
        PrintWriter out = response.getWriter();
        try {
            output= JSON.toJSONString(outPutInformation);
            //输出文本格式
            System.out.println("\n返回：");
            System.out.println(output);
            out.println(output);
            //把输出流立即发送到请求
            out.flush();
            return;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
