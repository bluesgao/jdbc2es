package com.bluesgao.jdbc2es.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: JdbcUtils
 * Desc: TODO
 * Author: gaoxin11
 * Date: 2018/6/21 11:15
 * 1，获取连接
 * 2，sql语句
 * 3，获取sql执行者
 * 4，设置sql参数
 * 5，执行sql语句
 * 6，处理数据集
 * 7，释放资源
 **/
@Data
@Slf4j
public class JdbcUtils {

    private static Map<String, DataSource> dataSourceMap = null;

    static {
        dataSourceMap = new ConcurrentHashMap<>();
        String key = "";
        DataSource dataSource = createDataSource(null);
        if (dataSource != null && !dataSourceMap.containsKey(key)) {
            dataSourceMap.put(key, dataSource);
        }
    }

    /**
     * 功能描述:创建数据源
     *
     * @Param: 属性配置
     * @Return:
     * @Auther: gaoxin11
     * @Date: 2018/6/21 14:22
     **/
    public static DataSource createDataSource(Properties properties) {
        log.info("创建数据源");
        log.info("数据源配置：{}", properties.toString());
        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            log.error("创建数据源失败:{}", e.getMessage());
        }
        return dataSource;
    }

    /**
     * 功能描述:获取数据库连接
     *
     * @Param:
     * @Return:
     * @Auther: gaoxin11
     * @Date: 2018/6/21 14:22
     **/
    public Connection getConnection(String key) {
        log.info("获取数据库连接");
        Connection connection = null;
        try {
            connection = dataSourceMap.get(key).getConnection();
        } catch (SQLException e) {
            log.error("获取数据库连接失败:{}", e.getMessage());
        }
        return connection;
    }

    /**
     * 功能描述:查询
     *
     * @Param:
     * @Return:
     * @Auther: gaoxin11
     * @Date: 2018/6/21 14:23
     **/
    public List<Map<String, Object>> query(Connection connection, String sql, Object... params) {
        log.info("执行查询：sql:{}; params:{}", sql.toString(), params.toString());
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //获得连接
            //connection = getConnection();
            //获得preparedSttement对象进行预编译（？占位符）
            pst = connection.prepareStatement(sql);
            int paramsIndex = 1;
            for (Object p : params) {
                pst.setObject(paramsIndex++, p);
            }
            //执行sql语句获得结果集的对象
            rs = pst.executeQuery();
            //获得结果集中列的信息
            ResultSetMetaData rst = rs.getMetaData();
            //获得结果集的列的数量
            int column = rst.getColumnCount();
            //创建List容器
            List<Map<String, Object>> rstList = new ArrayList<Map<String, Object>>();
            //处理结果
            while (rs.next()) {
                //创建Map容器存取每一列对应的值
                Map<String, Object> m = new HashMap<String, Object>();
                for (int i = 1; i <= column; i++) {
                    m.put(rst.getColumnName(i), rs.getObject(i));
                }
                //将Map容器放入List容器中
                rstList.add(m);
            }
            return rstList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //关闭资源
            close(connection, pst, rs);
        }
    }

    /**
     * 功能描述:数据库连接资源关闭
     *
     * @Param:
     * @Return:
     * @Auther: gaoxin11
     * @Date: 2018/6/21 14:23
     **/
    public void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        log.info("关闭jdbc资源");
        // 关闭结果集对象
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("关闭结果集对象错误：{}", e.getMessage());
            }
        }

        // 关闭PreparedStatement对象
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.error("关闭PreparedStatement对象错误：{}", e.getMessage());
            }
        }

        // 关闭Connection 对象
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("关闭Connection对象错误：{}", e.getMessage());
            }
        }
    }
}
