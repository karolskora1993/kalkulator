/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator.models;

import java.util.ArrayList;

public class ONP {

    public static String wordToOperation(String str){
        
        String statement = str;
        String result = "";
        String tmp = "";
        
        for (int i = 0; i < statement.length(); i++) {
            tmp = "";
            while (statement.charAt(i) != ' ') {
                tmp += statement.charAt(i);
                i++;
                if (i >= statement.length()) {
                    break;
                }
            }
            
            if(tmp.equals("i")){
                result+=" +";
            }else if(tmp.equals("po")){
                result+=" *";
            }else if(tmp.equals("bez")){
                result+=" -";
            }else if(tmp.equals("przez")){
                result+=" /";
            }else {
                result+=" "+tmp;    
            }
                      
            
        }
        return result;
    }
    
    public static String generateONPNot(String str) {
        boolean op1 = false;
        boolean op2 = false;
        boolean val = true;
        ArrayList<String> list = new ArrayList<String>();
        String statement = str;
        //statement = statement.replaceAll(" ", "");
        String result = "";
        String tmp = "";

        for (int i = 0; i < statement.length(); i++) {
            tmp = "";
            while (statement.charAt(i) != ' ') {
                tmp += statement.charAt(i);
                i++;
                if (i >= statement.length()) {
                    break;
                }
            }
            //System.out.println(tmp);

            if (tmp.length() > 1) {
                result += tmp + " ";
            } else {
                if (tmp.equals("(")) {
                    int j = i;
                    int block = 1;
                    while (block > 0) {
                        j++;
                        if (statement.charAt(j) == '(') {
                            block++;
                        } else if (statement.charAt(j) == ')') {
                            block--;
                        }
                    }
                    result += generateONPNot(statement.substring(i + 1, j));
                    i = j;
                    
                } else if ((tmp.equals("+")
                        || tmp.equals("-"))) {
                    if (!op1) {
                        op1 = true;
                        list.add(tmp);
                    } else {
                        result += " " + list.get(list.size() - 1) + " ";
                        list.remove(list.size() - 1);
                        list.add(tmp);
                    }
                   
                } else if (tmp.equals("*")
                        || tmp.equals("/")
                        || tmp.equals("^")) {
                    list.add(tmp);
                    op2 = true;
                   
                } else {
                    if (op2) {
                        result += " " + tmp + " " + list.get(list.size() - 1) + " ";
                        list.remove(list.size() - 1);
                        op2 = false;
                    } else {
                        
                            result += " " + tmp + " ";
                       
                    }
                }
            }
        }
        while (list.size() > 0) {
            result += " "+list.get(list.size() - 1) + " ";
            list.remove(list.size() - 1);
        }

        return result;
    }

    public static double calc(String str) {
        double a = 0.0;
        double b = 0.0;
        double w = 0.0;
        String c;
        String tmp = "";
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listTmp = new ArrayList<String>();
        boolean br = false;

        for (int i = str.length() - 1; i >= 0; i--) {
            
            while (str.charAt(i) != ' ') {
                tmp = str.charAt(i)+tmp;
                if (i > 0) {
                    i--;
                } else {
                    
                    break;
                }
            }
            
            
            if (tmp.length() > 0) {
                System.out.println(tmp);
                list.add(tmp);
                tmp = "";
            }

        }

        do {

            if (!list.isEmpty()) {
                c = list.get(list.size() - 1);
                list.remove(list.size() - 1);

                if (c.equals("+")) {
                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    b = Double.parseDouble(c.replaceAll(" ", "."));

                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    a = Double.parseDouble(c.replaceAll(" ", "."));

                    w = a + b;

                    listTmp.add(Double.toString(w));
                } else if (c.equals("-")) {
                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    b = Double.parseDouble(c.replaceAll(" ", "."));

                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    a = Double.parseDouble(c.replaceAll(" ", "."));

                    w = a - b;

                    listTmp.add(Double.toString(w));
                } else if (c.equals("*")) {
                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    b = Double.parseDouble(c.replaceAll(" ", "."));

                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    a = Double.parseDouble(c.replaceAll(" ", "."));

                    w = a * b;

                    listTmp.add(Double.toString(w));
                } else if (c.equals("/")) {
                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    b = Double.parseDouble(c.replaceAll(" ", "."));

                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    a = Double.parseDouble(c.replaceAll(" ", "."));

                    w = a / b;

                    listTmp.add(Double.toString(w));
                } else if (c.equals("^")) {
                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    b = Double.parseDouble(c.replaceAll(" ", "."));

                    c = listTmp.get(listTmp.size() - 1);
                    listTmp.remove(listTmp.size() - 1);
                    a = Double.parseDouble(c.replaceAll(" ", "."));

                    if (b == 0) {
                        w = 1;
                    } else {
                        w = a;
                        int i = 1;
                        while (i < (int) b) {
                            w *= w;
                            i++;
                        }
                    }
                    listTmp.add(Double.toString(w));
                } else {
                    listTmp.add(c);
                }
            }
        } while (list.size() > 0);

        return w;
    }
    
    public static double getSolution(String str){
        String tmp;
        tmp = wordToOperation(str);
        tmp = generateONPNot(tmp);
        return calc(tmp);
    }    
}
