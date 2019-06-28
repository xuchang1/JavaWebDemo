package jdbc_demo;

import java.sql.*;

/**
 * 多次执行statement.execute(String sql)操作，只能获得最新的结果
 * statement.execute(String sql)在执行已存储过程或动态执行未知SQL字符串时，可能返回多个结果集，通过getMoreResults()方法能获取到后续的结果
 */
public class demo2 {
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void main(String[] args) {
        demo1 demo1 = new demo1();
        Connection con = demo1.getConnection();

        try {
            Statement statement = con.createStatement();

            statement.execute("select * from student");  //true
            statement.execute("select count(1) from student where name = '李磊'"); //true
            statement.execute("update student set age = '20' where name = '李磊'"); //false

            int updateCount = statement.getUpdateCount();
//            statement.getMoreResults();
            ResultSet resultSet2 = statement.getResultSet();
//            statement.getMoreResults();
            ResultSet resultSet1 = statement.getResultSet();

            if (resultSet1 != null) {
                while (resultSet1.next()) {
                    String name = resultSet1.getString("name");
                    int age = resultSet1.getInt("age");
                    String sex = resultSet1.getString("sex");
                    System.out.print("姓名 : " + name);
                    System.out.print(" 年龄 : " + age);
                    System.out.print(" 性别 : " + sex);
                    System.out.println();
                }
            } else {
                System.out.println("获取到的结果resultSet1为null");
            }

            if (resultSet2 != null) {
                while (resultSet2.next()) {
                    String s = resultSet2.getString("count(1)");
                    System.out.println("查询到的数量 : " + s);
                }
            } else {
                System.out.println("获取到的结果resultSet2为null");
            }

            System.out.println(" 操作的行数 : " + updateCount);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
