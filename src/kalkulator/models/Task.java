/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.models;

/**
 * Klasa przechowywująca zadanie obliczeniowe i jego wynik
 *
 * @author Michał
 */
public class Task {
    String task="";
    double result;
    
/**
* Funkcja pobierająca zapisane zadanie
*/
public String getTask()
{
    return task;
}

/**
* Funkcja zapisujaca "tresc" zadania
*/
public void setTask(String task)
{
    this.task=task;
}

/**
* Funkcja pobierająca wynik zadania
*/
public double getResult()
{
    return result;
}

/**
* Funkcja zapisująca wynik zadania
*/
public void setResult(double result)
{
    this.result=result;
}
    
}
