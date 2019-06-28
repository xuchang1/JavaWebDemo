package jdbc_demo;

import java.sql.*;

/**
 * 1、Class.forName("com.mysql.jdbc.Driver") : 加载相应数据库驱动
 * 2、DriverManager.getConnection(url, username, password) : 获取Connection对象
 * 3、通过Connection对象,生成Statement或PreparedStatement对象，用来执行sql
 *
 * statement.execute(String sql) : 返回结果为Boolean类型，如果返回第一个结果为ResultSet对象，则为 true，
 *                                      如果为更新计数或者不存在任何结果，则为false
 *          根据返回结果的不同，使用statement.getResultSet()或statement.getUpdateCount()方法获取相应的结果。
 */
public class demo1 {

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
            ResultSet resultSet = null;
//            boolean flag = statement.execute("select * from student");  //true
//            boolean flag  = statement.execute("select count(1) from student where name = '李磊'"); //true
            boolean flag = statement.execute("update student set age = '20' where name = '李磊'"); //false

            System.out.println(flag);
            if (flag) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                /*String s = resultSet.getString("count(1)");
                System.out.println(s);*/
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String sex = resultSet.getString("sex");
                    System.out.print("姓名 : " + name);
                    System.out.print(" 年龄 : " + age);
                    System.out.print(" 性别 : " + sex);
                    System.out.println();
                }
            } else {
                int updateCount = statement.getUpdateCount();
                System.out.println("操作的行数 : " + updateCount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
