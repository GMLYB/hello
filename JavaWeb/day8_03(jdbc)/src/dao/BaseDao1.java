package dao;

import util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了针对数据表的通用操作
 */

public abstract class BaseDao1<T> {

    private Class<T> tClass = null;

    {
        //获取当前BaseDao子类继承父类中的泛型
        //子类对象获得带泛型的父类
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        // ParameterizedType 参数化类型，即泛型
        // 将Type转化为参数化类型(即泛型)
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        // getActualTypeArguments() 获取参数化类型的数组，泛型可能有多个
        Type[] typeArguments = paramType.getActualTypeArguments();//获取父类的泛型列表
        tClass = (Class<T>) typeArguments[0];//泛型的第一个参数
    }


    //通用的增、删、改操作
    public int update(Connection conn, String sql, Object ...args){
        PreparedStatement ps = null;
        try {
            //1. 预编译SQL语句 返回 PreparedStatement 的实例
            ps = conn.prepareStatement(sql);
            //2. 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //3. 执行SQL
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //4. 关闭资源
            JDBCUtils.closeResourse(null,ps);
        }
        return 0;
    }


    // 获取一个对象的方法
    public T getInstance(Connection conn,String sql, Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            if (rs.next()){
                T t = tClass.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    Object colValue = rs.getObject(i + 1);
                    // 获取 列的列名 getColumnName() -----> 不推荐使用
//                    String columnName = metaData.getColumnName(i + 1);
                    // 获取 列的别名 getColumnLabel()
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field field = tClass.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,colValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(null,ps,rs);
        }
        return null;
    }


    // 获取一个list对象的方法
    public List<T> getForlist(Connection conn , String sql, Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            List<T> list = new ArrayList<>();
            while (rs.next()){
                T t = tClass.newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    Object colValue = rs.getObject(i + 1);
                    // 获取 列的列名 getColumnName() -----> 不推荐使用
//                    String columnName = metaData.getColumnName(i + 1);
                    // 获取 列的别名 getColumnLabel()
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = tClass.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,colValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(null,ps,rs);
        }
        return null;
    }

    //用于查询特殊值的通用方法
    public <E> E getValue(Connection conn, String sql, Object ... args) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();

            if (rs.next()){
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResourse(null,ps,rs);
        }
        return null;
    }

}
