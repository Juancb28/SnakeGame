package ec.edu.DataAcces.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLiteDataHelper {
    //private static String DBPhatConnection = "jdbc:sqlite:database/data.sqlite";
    private static String DBPhatConnection = "jdbc:sqlite:database/data.sqlite";
    private static Connection conn = null ;
    protected static synchronized Connection opConnection() throws Exception {
          try {
            if (conn == null) {
                conn = DriverManager.getConnection(DBPhatConnection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
        return conn;
    }
    
    protected static void closeConnection() throws Exception {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    

    
}
