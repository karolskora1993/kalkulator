
package kalkulator.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DataBase {
    public static Connection conn;
          
    public DataBase() throws SQLException{
        conn = getConnection(); 
        
    }
    
    /**
     * Połączenie z bazą danych
     * @return
     * @throws SQLException 
     */   
    public static Connection getConnection() throws SQLException {
        String host = "mysql://localhost";
        String db = "java";
        String connectionString = "jdbc:" + host + "/" + db;
        String username = "root";
        String _password = "";
        Connection _conn = DriverManager.getConnection(connectionString, username, _password);
        return _conn;
    }
    
    /**
     * Wyświetlenie wszystkich danych
     * @throws SQLException 
     */
    public static void selectAll() throws SQLException {
        if (conn.isValid(10)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM  `model`");

            while (rs.next()) {
                int id = rs.getInt("id_Model");
                String model = rs.getString("Model");
                
                System.out.println(id+": "+model);
            }
            System.out.println("---------------");
        }
    }
 
    public static boolean addModel(String model, int val) throws SQLException {
        if (conn.isValid(100)) {
            
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO `model` (`Id_Model`, `Model`, `Unknown_Value`) VALUES (null, ?, ?)");
                stmt.setString(1, model);
                stmt.setInt(2, val);

                int rows = stmt.executeUpdate();
                return true;
           
                 
        }
        return false;
    }
    
    /**
     * Sprawdzenie czy dane konto już istnieje
     * @param nick
     * @return
     * @throws SQLException 
     */
    public static boolean selectNick(String nick) throws SQLException {
        if (conn.isValid(10)) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM  `PlayerShip` WHERE `nick` = ?");
            stmt.setString(1, nick);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                return rs.getString("nick").equals(nick);
            }
        }
        return false;
    }   

    /**
     * Dodanie nowego konta
     * @param nick
     * @param password
     * @return
     * @throws SQLException 
     */
    public static boolean create(String nick, String password) throws SQLException {
        if (conn.isValid(10)) {
            if(!selectNick(nick)){
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO `PlayerShip` (`nick`, `password`) VALUES (?, ?)");
                stmt.setString(1, nick);
                stmt.setString(2, password);

                int rows = stmt.executeUpdate();
                return true;
            }else{
                return false;
            }
                 
        }
        return false;
    }

    /**
     * Sprawdzenie poprawności danych (loginu i hasła) potrzebnych do logowania
     * @param nick
     * @param password
     * @return
     * @throws SQLException 
     */
    public static boolean login(String nick, String password) throws SQLException {
        if (conn.isValid(10)) {

            PreparedStatement stmt = conn.prepareStatement("SELECT `password` FROM  `PlayerShip` WHERE `nick` = ?");
            stmt.setString(1, nick);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getString("password").equals(password);
            }
        }
        return false;
    }
    
    /**
     * Usuwanie danego gracza z bazy danych
     * @param nick
     * @param password
     * @return
     * @throws SQLException 
     */
    public static int delNick(String nick, String password) throws SQLException {
        if (conn.isValid(10)) {

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM `PlayerShip` WHERE `nick` = ? AND `password` = ?");
            stmt.setString(1, nick);
            stmt.setString(2, password);
            int rows = stmt.executeUpdate();

            return rows;
            // }else{
            // getConnection();   
        }
        return 0;

    }
    
    public static Object[] getExistingTasts(){
         String[] tasks={"brak wprowadzonych zadań"};
        return  tasks;
    } 
    
        
}
