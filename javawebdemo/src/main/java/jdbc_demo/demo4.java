package jdbc_demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demo4 {
    public static void main(String[] args) {
        Conn conn = new Conn();
        Connection connection = conn.getCon();

        try {
            PreparedStatement sql = connection.prepareStatement("select * from student where name = ?");
            sql.setString(1, "李磊");
            ResultSet resultSet = sql.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                System.out.print("姓名 : " + name);
                System.out.print(" 年龄 : " + age);
                System.out.print(" 性别 : " + sex);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
