
package kalkulator.views;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author Karol
 */
public class MyJLabel extends JLabel{
    private int value;
    
    public MyJLabel(){
        super();
    }
    public MyJLabel(Icon image){
        super(image);
    }
    
    public void setValue(int value){
        this.value=value;
    }
    
    public int getValue(){
        return this.value;
    }
    
    
}
