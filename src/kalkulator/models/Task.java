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
    int id;
    String task;
    double result;
 
/**
* Konstruktor "pustego" zadania
*/    
Task()
{
    this.id=0;
    this.result=0;
    this.task="";
}
    
/**
* Konstruktor zadania ze znana "trescia" i wynikiem
*/
Task(int id, String task, double result)
{
    this.id=id;
    this.result=result;
    this.task=task;
}
        
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
