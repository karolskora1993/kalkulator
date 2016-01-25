/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa do zapewniająca połaczenie z baza danych
 *
 * @author Michał
 */
public class DataBase {
 
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:baza.db";
 
    private Connection conn;
    private Statement stat;
 
    public DataBase() {
        try {
            Class.forName(DataBase.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
        }
 
        createTables();
    }

    /**
    * Funkcja tworzaca nowe tabele w bazie danych
    */
    public boolean createTables()  {
        String createUsers = "CREATE TABLE IF NOT EXISTS users (id_user INTEGER PRIMARY KEY AUTOINCREMENT, nick varchar(255), hash varchar(255))";
        String createTasks = "CREATE TABLE IF NOT EXISTS tasks (id_task INTEGER PRIMARY KEY AUTOINCREMENT, task varchar(255), result float)";
        
        try {
            stat.execute(createUsers);
            stat.execute(createTasks);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            
            return false;
        }
        return true;
    }
    
    /**
    * Funkcja zapisujaca w bazie nowego uzytkownika
    */
   public boolean newUser(String nick, String hash) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into users values (NULL, ?, ?);");
            prepStmt.setString(1, nick);
            prepStmt.setString(2, hash);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu nowego uzytkownika");
            return false;
        }
        return true;
    }
   
    /**
    * Funkcja zapisujaca w bazie nowe zadanie
    */
    public boolean newTask(String task, double result) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into tasks values (NULL, ?, ?);");
            prepStmt.setString(1, task);
            prepStmt.setString(2, String.valueOf(result));
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad zapisie nowego zadania do bazy");
            return false;
        }
        System.out.print("Dodano zadanie i wynik do bazy");
        return true;
    }
 

    /**
    *Funkcja pobierająca zapisane zadania z bazy
    */
    public String[] selectTasks() {
        String[] tasks = null;
        try {
            ResultSet data = stat.executeQuery("SELECT * FROM tasks");
            System.out.print(data);
            String task;
            int i=0;
            while(data.next()) {
                tasks[i] = data.getString("task");
                i++;
            }
        } catch (SQLException e) {
            return null;
        }
        return tasks;
    }
 
    /**
    * Funkcja zamykająca połączenie z bazą
    */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
        }
    } 
    
    
    
}
