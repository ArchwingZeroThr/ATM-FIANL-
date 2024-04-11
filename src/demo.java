import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demo {

    public static int handleHello(String p,String clientIpAddress) throws SQLException {
        // 与数据库建立联系
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/card?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("123456");
        Connection connection = dataSource.getConnection();
        // 创建SQL语句查询是否存在等于p的cid值

        String sql = "SELECT * FROM cardinfo WHERE cid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, p);
        // 发送已经解析好的sql语句到数据库服务器中
        ResultSet rs = preparedStatement.executeQuery();
        int flag=0;
        if(rs.next()){
            flag=1;
        }
        if (flag == 0) {
            String insertSql = "INSERT INTO olg (ip,user,message1,op,message2) VALUES (?,?,?,?,?)";
            PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSql);
            // 假设您要插入的操作信息是"No record found for cid: " + p
            insertPreparedStatement.setString(1,  clientIpAddress);
            insertPreparedStatement.setString(2, p);
            insertPreparedStatement.setString(3, "401 ERROR!");
            insertPreparedStatement.setString(4, "HELO "+p);
            insertPreparedStatement.setString(5, "No record found for cid: " + p);
            int rowsAffected = insertPreparedStatement.executeUpdate();
            // 检查是否成功插入记录
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully into olog table.");
            }
        }
        if (flag == 1) {
            String insertSql = "INSERT INTO olg (ip,user,message1,op,message2) VALUES (?,?,?,?,?)";
            PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSql);
            // 假设您要插入的操作信息是"No record found for cid: " + p
            insertPreparedStatement.setString(1,  clientIpAddress);
            insertPreparedStatement.setString(2, p);
            insertPreparedStatement.setString(3, "500 AUTH REQUIRED!");
            insertPreparedStatement.setString(4, "HELO "+p);
            insertPreparedStatement.setString(5, "record found for cid: " + p);
            int rowsAffected = insertPreparedStatement.executeUpdate();
            // 检查是否成功插入记录
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully into olog table.");
            }
        }
        // 释放资源
        preparedStatement.close();
        connection.close();
        return flag;
    }
    public static int handlePASS(String p,String pw,String clientIpAddress) throws SQLException {
        // 与数据库建立联系
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/card?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("123456");
        Connection connection = dataSource.getConnection();
        // 创建SQL语句查询是否存在等于p的cid值

        String sql = "SELECT * FROM cardinfo WHERE cid = ? AND pw = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, p);
        preparedStatement.setString(2, pw);
        // 发送已经解析好的sql语句到数据库服务器中
        ResultSet rs = preparedStatement.executeQuery();
        int flag=1;
        if(rs.next()){
            flag=2;
        }
        // 释放资源
        if (flag == 1) {
            String insertSql = "INSERT INTO olg (ip,user,message1,op,message2) VALUES (?,?,?,?,?)";
            PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSql);
            // 假设您要插入的操作信息是"No record found for cid: " + p
            insertPreparedStatement.setString(1,  clientIpAddress);
            insertPreparedStatement.setString(2, p);
            insertPreparedStatement.setString(3, "401 ERROR!");
            insertPreparedStatement.setString(4, "PASS "+p);
            insertPreparedStatement.setString(5, "Password and account do not match");
            int rowsAffected = insertPreparedStatement.executeUpdate();
            // 检查是否成功插入记录
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully into olog table.");
            }
        }
        if (flag == 2) {
            String insertSql = "INSERT INTO olg (ip,user,message1,message2,op) VALUES (?,?,?,?,?)";
            PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSql);
            // 假设您要插入的操作信息是"No record found for cid: " + p
            insertPreparedStatement.setString(1,  clientIpAddress);
            insertPreparedStatement.setString(2, p);
            insertPreparedStatement.setString(3, "525 OK!");
            insertPreparedStatement.setString(4, "PASS "+p);
            insertPreparedStatement.setString(5, "Password and account match");
            int rowsAffected = insertPreparedStatement.executeUpdate();
            // 检查是否成功插入记录
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully into olog table.");
            }
        }
        preparedStatement.close();
        connection.close();
        return flag;
    }
    public static double handleBALA(String p, String pw,String clientIpAddress) throws SQLException {
        // 与数据库建立联系
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/card?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("123456");
        Connection connection = dataSource.getConnection();
        double money=-1;
        try {
            // 创建SQL语句查询用户的金额
            String sql = "SELECT money FROM cardinfo WHERE cid = ? AND pw = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, p);
            preparedStatement.setString(2, pw);
            ResultSet rs = preparedStatement.executeQuery();

            // 检查结果集中是否有数据
            if (rs.next()) {
                money = rs.getDouble("money"); // 获取用户的金额
                System.out.println("User's balance: " + money); // 输出用户的金额
                String insertSql = "INSERT INTO olg (ip,user,message1,message2,op) VALUES (?,?,?,?,?)";
                PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSql);
                // 假设您要插入的操作信息是"No record found for cid: " + p
                insertPreparedStatement.setString(1,  clientIpAddress);
                insertPreparedStatement.setString(2, p);
                insertPreparedStatement.setString(3, "AMNT:" + String.format("%.2f", money));
                insertPreparedStatement.setString(4, "BALA");
                insertPreparedStatement.setString(5, "Balance inquiry successful");
                int rowsAffected = insertPreparedStatement.executeUpdate();
                // 检查是否成功插入记录
                if (rowsAffected > 0) {
                    System.out.println("Record inserted successfully into olog table.");
                }
            } else {
                // 如果没有找到用户或密码不正确，输出错误信息
                System.out.println("401 sp ERROR! User not found or incorrect password.");
            }
            preparedStatement.close();
        } finally {
            // 释放资源

            connection.close();
        }
        return money;
    }
    public static int handleWDRA(String p, String pw, double value,String clientIpAddress) throws SQLException {
        // 与数据库建立联系
        int f1=0;
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://127.0.0.1:3306/card?characterEncoding=utf8&useSSL=false");
        ((MysqlDataSource) dataSource).setUser("root");
        ((MysqlDataSource) dataSource).setPassword("123456");
        Connection connection = dataSource.getConnection();
        double userMoney = -1;
        double updatedMoney = -1;

        try {
            // 创建SQL语句查询用户的金额
            String sql = "SELECT money FROM cardinfo WHERE cid = ? AND pw = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, p);
            preparedStatement.setString(2, pw);
            ResultSet rs = preparedStatement.executeQuery();

            // 检查结果集中是否有数据
            if (rs.next()) {
                userMoney = rs.getDouble("money"); // 获取用户的金额
                if (userMoney >= value) {
                    // 余额足够，更新用户的余额
                    updatedMoney = userMoney - value;
                    String updateSql = "UPDATE cardinfo SET money = ? WHERE cid = ?";
                    PreparedStatement updatePreparedStatement = connection.prepareStatement(updateSql);
                    updatePreparedStatement.setDouble(1, updatedMoney);
                    updatePreparedStatement.setString(2, p);
                    int updatedRows = updatePreparedStatement.executeUpdate();
                    if (updatedRows > 0) {
                        f1=1;
                        System.out.println("525 OK! Withdrawal successful. New balance: " + updatedMoney);
                        String insertSql = "INSERT INTO olg (ip,user,message1,message2,op) VALUES (?,?,?,?,?)";
                        PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSql);
                        // 假设您要插入的操作信息是"No record found for cid: " + p
                        insertPreparedStatement.setString(1,  clientIpAddress);
                        insertPreparedStatement.setString(2, p);
                        insertPreparedStatement.setString(3, "525 OK!");
                        insertPreparedStatement.setString(4, "WDRA "+value);
                        insertPreparedStatement.setString(5, "Withdrawal successful. New balance: " + updatedMoney);
                        int rowsAffected = insertPreparedStatement.executeUpdate();
                        // 检查是否成功插入记录
                        if (rowsAffected > 0) {
                            System.out.println("Record inserted successfully into olog table.");
                        }
                    } else {
                        String insertSql = "INSERT INTO olg (ip,user,message1,message2,op) VALUES (?,?,?,?,?)";
                        PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSql);
                        // 假设您要插入的操作信息是"No record found for cid: " + p
                        insertPreparedStatement.setString(1,  clientIpAddress);
                        insertPreparedStatement.setString(2, p);
                        insertPreparedStatement.setString(3, "401 ERROR!");
                        insertPreparedStatement.setString(4, "WDRA "+value);
                        insertPreparedStatement.setString(5, "Withdrawal unsuccessful.");
                        int rowsAffected = insertPreparedStatement.executeUpdate();
                        // 检查是否成功插入记录
                        if (rowsAffected > 0) {
                            System.out.println("Record inserted successfully into olog table.");
                        }
                        System.out.println("401 ERROR! Failed to update balance.");
                    }
                } else {
                    String insertSql = "INSERT INTO olg (ip,user,message1,message2,op) VALUES (?,?,?,?,?)";
                    PreparedStatement insertPreparedStatement = connection.prepareStatement(insertSql);
                    // 假设您要插入的操作信息是"No record found for cid: " + p
                    insertPreparedStatement.setString(1,  clientIpAddress);
                    insertPreparedStatement.setString(2, p);
                    insertPreparedStatement.setString(3, "401 ERROR!");
                    insertPreparedStatement.setString(4, "WDRA "+value);
                    insertPreparedStatement.setString(5, "Withdrawal unsuccessful.Insufficient funds.");
                    int rowsAffected = insertPreparedStatement.executeUpdate();
                    // 检查是否成功插入记录
                    if (rowsAffected > 0) {
                        System.out.println("Record inserted successfully into olog table.");
                    }
                    // 余额不足，输出错误信息
                    System.out.println("401 ERROR! Insufficient funds.");
                }
            }
            if (preparedStatement != null) preparedStatement.close();
        } finally {
            // 释放资源

            if (connection != null) connection.close();
        }
        return f1;
    }

}
