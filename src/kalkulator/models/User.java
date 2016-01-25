/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.models;

/**
 *  Klasa przechowywująca dane użytkownika
 * 
 * @author apple
 */
public class User {
    private boolean loggedIn=false;
    private String login="guest";
    private String hash;
    
    /**
     * Funkcja sprawdzająca czy user jest zalogowany
     */
    public boolean isLoggedIn(){
        return loggedIn;
    }
    
    /**
     * Funkcja zwracająca login usera
     */
    public String getLogin(){
        return login;
    }
    
    /**
     * Funkcja zwracająca hash
     */
    public String getHash(){
        return hash;
    }
    
}
