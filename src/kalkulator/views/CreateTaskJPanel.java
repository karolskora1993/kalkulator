package kalkulator.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import kalkulator.models.DataBase;


/**
 * Klasa reprezentująca panel wprowadzania zadania
 *
 * @author Karol
 */
public class CreateTaskJPanel extends JPanel
{

    private BackgroundComponent bgComponent;
    private JTextField username;
    private JPasswordField password;
    private JButton okButton;
    private JButton guestSessionButton;
    private final Frame parent;

    /**
     *
     * @param parent Okno zawierające panel
     */
    public CreateTaskJPanel(Frame parent)
    {
        this.parent=parent;
        initializeComponent();
    }

    /**
     * Funkcja inicjalizująca komponenty
     */
    private void initializeComponent()
    {
        setSize(FrameSize.getWidth(), FrameSize.getHeight());
        setLayout(new BorderLayout());
        
        bgComponent=new BackgroundComponent("loginBG.jpg");
        bgComponent.setLayout(new BorderLayout());
        
        JLabel chooseExistingLabel=new JLabel("Wybierz spośród istniejących zadań:");
        chooseExistingLabel.setForeground(Color.WHITE);
        
        String[] tasks=(String[])DataBase.getExistingTasts();
        JComboBox existingTasks=new JComboBox(tasks);
        
        JPanel existingTasksContainer=new JPanel(new GridLayout(1, 2));
        existingTasksContainer.setOpaque(false);
        existingTasksContainer.add(chooseExistingLabel);
        existingTasksContainer.add(existingTasks);
        bgComponent.add(existingTasksContainer, BorderLayout.NORTH);
        
        JTextArea taskTextArea=new JTextArea("Wprowadź zadanie");
        bgComponent.add(taskTextArea, BorderLayout.CENTER);
        
        JPanel buttonContainer=new JPanel(new GridLayout(2, 1));
        buttonContainer.setOpaque(false);
        
        JPanel numberButtonsContainer=new JPanel(new GridLayout(1, 5));
        numberButtonsContainer.setOpaque(false);
        
        for(int i=1;i<6;i++){
            numberButtonsContainer.add((new JLabel(new ImageIcon("images/Button"+i+".png"))));
        }
        buttonContainer.add(numberButtonsContainer);
        
        JPanel operatonButtonsContainer=new JPanel(new GridLayout(1, 5));
        operatonButtonsContainer.setOpaque(false);
        
        JLabel buttonBez=new JLabel(new ImageIcon("images/buttonBez.png"));
        buttonBez.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                
            }
});
        operatonButtonsContainer.add(buttonBez);
        
        JLabel buttonI=new JLabel(new ImageIcon("images/buttonI.png"));
        buttonBez.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                
            }
});
        operatonButtonsContainer.add(buttonI);
        
        JLabel buttonPo=new JLabel(new ImageIcon("images/buttonPo.png"));
        buttonBez.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                
            }
});
        operatonButtonsContainer.add(buttonPo);
        
        JLabel buttonPrzez=new JLabel(new ImageIcon("images/buttonPrzez.png"));
        buttonBez.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                
            }
});
        operatonButtonsContainer.add(buttonPrzez);
        
        JLabel buttonMam=new JLabel(new ImageIcon("images/buttonMam.png"));
        buttonMam.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                
                parent.hideCreateTaskJPanel();
                parent.showResultPanel();
            }
});
        operatonButtonsContainer.add(buttonMam);
        
        buttonContainer.add(operatonButtonsContainer);
        
        bgComponent.add(buttonContainer, BorderLayout.SOUTH);
        
        
        add(bgComponent);
    }
}