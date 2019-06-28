1、demo1 : jdbc进行数据库连接操作，Class.forName(String DriverName)加载数据库驱动，
            DriverManager.getConnection(url, username, password)方法获取数据库连接对象
            con.createStatement()获取Statement对象执行具体sql。
            
            statement.execute(String sql) : 返回结果为Boolean类型，如果返回第一个结果为ResultSet对象，则为 true，
                                        如果为更新计数或者不存在任何结果，则为false
                         根据返回结果的不同，使用statement.getResultSet()或statement.getUpdateCount()方法获取相应的结果。
                         
2、demo2 : execute()方法在执行已存储过程或动态执行未知SQL字符串时，可能会返回多个结果集，正常情况多次执行sql，只能获得最后的结果。

3、demo3 : statement的executeQuery()和executeUpdate()方法。

4、