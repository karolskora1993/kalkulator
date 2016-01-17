/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.main;

import kalkulator.models.DefaultOptions;
import kalkulator.models.User;
import kalkulator.views.Frame;

/**
 *
 * @author apple
 */
public class Kalkulator {

    /**
     * @param args argumenty wiersza poleceń
     */
  public static void main(String args[]) {
            
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                DefaultOptions defaultOptions=new DefaultOptions("defaultOptions.xml");
                User user=new User();
                new Frame(defaultOptions, user).setVisible(true);
            }
        });
    }
    
}
