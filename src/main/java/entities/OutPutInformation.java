package entities;

/**
 * @author 19892
 */
public class OutPutInformation<T> {
    private int code=0;
    private String message="success";
    private T data;
    private String session;

    public void setCode(int code){this.code=code;}

    public int getCode(){return code;}

    public void setMessage(String message){this.message=message;}

    public String getMessage(){return message;}

    public void setData(T data){this.data=data;}

    public T getData(){return data;}

    public void setSession(String session){this.session=session;}

    public String getSession(){return session;}

    public OutPutInformation of(int code){
        OutPutInformation outPutInformation=new OutPutInformation();
        outPutInformation.setCode(code);
        switch (code){
            case 1:outPutInformation.setMessage("所请求的数据为空");
            break;
            case -1:outPutInformation.setMessage("传入参数不完整或参数名错误或一次传参过多");
            break;
            case -2:outPutInformation.setMessage("账号或密码错误");
            break;
            case -3:outPutInformation.setMessage("后端错误,请截图并联系后端");
            break;
            case -4:outPutInformation.setMessage("登陆状态错误");
            default:break;
        }
        return outPutInformation;
    }

}
