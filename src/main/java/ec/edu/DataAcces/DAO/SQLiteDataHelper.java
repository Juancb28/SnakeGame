package ec.edu.DataAcces.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase abstracta SQLiteDataHelper.
 * 
 * Proporciona métodos para gestionar la conexión a una base de datos SQLite. 
 * Esta clase es utilizada por otras clases DAO para establecer y cerrar conexiones con la base de datos.
 */

public abstract class SQLiteDataHelper {
    //private static String DBPhatConnection = "jdbc:sqlite:database/data.sqlite";
    private static String DBPhatConnection = "jdbc:sqlite:database/data.sqlite";
    private static Connection conn = null ;

     /**
     * Obtiene una conexión a la base de datos SQLite.
     * 
     * Si la conexión aún no ha sido establecida, la crea utilizando la ruta especificada en DBPhatConnection.
     * 
     * @return La conexión a la base de datos SQLite.
     * @throws Exception Si ocurre un error al establecer la conexión.
     */
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
    
    /**
     * Cierra la conexión a la base de datos SQLite si está abierta.
     * 
     * @throws Exception Si ocurre un error al cerrar la conexión.
     */
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
