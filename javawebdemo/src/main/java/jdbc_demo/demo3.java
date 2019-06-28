package jdbc_demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * executeQuery() : 执行查询操作，返回单个resultSet对象
 * executeUpdate() : 执行INSERT、UPDATE、DELETE语句或者不返回任何内容的SQL语句，如 DDL 语句。
 *                  (1) 对于SQL数据操作语言(DML)语句，返回行计数 (2)对于什么都不返回的SQL语句，返回0
 */
public class demo3 {
    public static void main(String[] args) {
        Conn conn = new Conn();
        Connection connection = conn.getCon();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                System.out.print("姓名 : " + name);
                System.out.print(" 年龄 : " + age);
                System.out.print(" 性别 : " + sex);
                System.out.println();
            }

            int i = statement.executeUpdate("update student set age = '20' where name = '李磊'");
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
