package servlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;


public class Crawler {
    static final String url="https://sduonline.cn/isduapi/api/auth/login/system/";
    private String u;
    private String p;
    public Crawler(String u, String p){
        this.u=u;
        this.p=p;
    }
    public Document doget() throws IOException {
        final int MAX =10;
        int time =0;
        Document doc =null;
        while (time<MAX){
            doc = Jsoup
                    .connect(url)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .timeout(1000*30)
                    .data("p",p)
                    .data("u",u)
                    .post();
            return doc;
        }
        return doc;
    }
    public int getCode(String u,String p) throws IOException {
        this.p=p;
        this.u=u;
        Document document=doget();
        JSONObject jsonObject= JSON.parseObject(document.text());
        return jsonObject.getInteger("code");
    }
}
