package dao;

import util.BeanUtil;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author 19892
 */
public class BaseDao<T> {
    public int update(String sql,Object...objects){
        Connection connection=null;
        try{
            connection = ConnectionUtil.getConnection();
            assert connection != null;
            PreparedStatement ps=connection.prepareStatement(sql);
            if(objects!=null) {
                for (int i=0;i<objects.length;i++) {
                    ps.setObject(i+1,objects[i]);
                }
            }
                return ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            ConnectionUtil.close(connection);
        }
    }
    public int findInt(String sql,Object...objects){
        Connection connection=null;
        try {
            connection=ConnectionUtil.getConnection();
            assert connection != null;
            PreparedStatement ps=connection.prepareStatement(sql);
            if(objects!=null) {
                for(int i=0;i<objects.length;i++){
                    ps.setObject(i+1,objects[i]);
                }
            }
            ps.setMaxRows(1);
            ResultSet rs=ps.executeQuery();
            int t = rs.getInt(1);
            return t;
        }catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }finally {
            ConnectionUtil.close(connection);
        }
    }

    public T find(Class<T>tClass,String sql,Object...objects){
        T t=null;
        Connection connection=null;
        try {
            connection=ConnectionUtil.getConnection();
            assert connection != null;
            PreparedStatement ps=connection.prepareStatement(sql);
            if(objects!=null) {
                for(int i=0;i<objects.length;i++){
                    ps.setObject(i+1,objects[i]);
                }

            }
                ps.setMaxRows(1);
                ResultSet rs=ps.executeQuery();
                t = BeanUtil.toBean(tClass,rs);
                return t;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }finally {
            ConnectionUtil.close(connection);
        }
    }
    public List<T> list(Class<T>tClass, String sql, Object... objects){
        List<T>list = null;
        Connection connection = null;
        try{
            connection = ConnectionUtil.getConnection();
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            if (objects != null) {
                for (int i=0; i<objects.length; ++i) {
                    ps.setObject(i + 1, objects[i]);
                }
            }
            ResultSet rs = ps.executeQuery();
            list = BeanUtil.toBeanList(tClass, rs);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            ConnectionUtil.close(connection);
        }
    }

}
