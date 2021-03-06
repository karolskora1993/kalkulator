package kalkulator.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import kalkulator.models.DataBase;
import kalkulator.models.DataBase2;
import kalkulator.models.ONP;
import kalkulator.models.ScaleImage;
import kalkulator.models.Task;

/**
 * Klasa reprezentująca panel wprowadzania zadania
 *
 * @author Karol
 */
public class CreateTaskJPanel extends JPanel {

    private BackgroundComponent bgComponent;
    private JTextField username;
    private JPasswordField password;
    private JButton okButton;
    private JButton guestSessionButton;
    private JTextArea taskTextArea;
    private final Frame parent;
    private String task = "";
    public java.util.List<JButton> numberButt = new ArrayList<>();
    String key = "0123456789";
    DataBase db = new DataBase();
    /**
     *
     * @param parent Okno zawierające panel
     */
    public CreateTaskJPanel(Frame parent) {
        this.parent = parent;
        initializeComponent();
    }

    /**
     * Funkcja inicjalizująca komponenty
     */
    private void initializeComponent() {
        setSize(FrameSize.getWidth(), FrameSize.getHeight());
        setLayout(new BorderLayout());

        bgComponent = new BackgroundComponent("loginBG.jpg");
        bgComponent.setLayout(new BorderLayout(10, 10));

        JLabel chooseExistingLabel = new JLabel("Wybierz spośród istniejących zadań:");
        chooseExistingLabel.setForeground(Color.WHITE);
         
        String tasks[]={"Brak zadań w bazie."};
        JComboBox existingTasks = new JComboBox(tasks);
        
        JPanel existingTasksContainer = new JPanel(new GridLayout(1, 2));
        existingTasksContainer.setOpaque(false);
        existingTasksContainer.add(chooseExistingLabel);
        existingTasksContainer.add(existingTasks);
        bgComponent.add(existingTasksContainer, BorderLayout.NORTH);

        taskTextArea = new JTextArea("Wprowadź zadanie");
        taskTextArea.setFont(new java.awt.Font("Segoe UI Black", 1, 50));
        bgComponent.add(taskTextArea, BorderLayout.CENTER);

        JPanel buttonContainer = new JPanel(new GridLayout(2, 1));
        buttonContainer.setOpaque(false);

//        JPanel numberButtonsContainer = new JPanel(new GridLayout(2, 5));
//        numberButtonsContainer.setOpaque(false);
//        for (int i = 0; i < key.length(); i++) {
//            numberButt.add(new JButton(key.substring(i, i + 1)));
//            numberButtonsContainer.add(numberButt.get(i));
//
//            numberButt.get(i).addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    Object o = e.getSource();
//                    JButton bt = null;
//                    String buttonText = "";
//                    
//                    if(o instanceof JButton)
//                        bt = (JButton)o;
//                    
//                    if(bt!=null){
//                        buttonText = bt.getText();
//                        task+=buttonText;
//                        
//                    }
//                }
//            });
//        }
//        buttonContainer.add(numberButtonsContainer);
        GridLayout gridLayout = new GridLayout(2, 5);
        gridLayout.setVgap(10);
        JPanel numberButtonsContainer = new JPanel(gridLayout);
        numberButtonsContainer.setOpaque(false);
        for (int i = 0; i < 10; i++) {
            Image image = new ImageIcon("images/Button" + i + ".png").getImage();
            MyJLabel numberJLabel = new MyJLabel(ScaleImage.getScaledImageIcon(image, FrameSize.getWidth()/13, FrameSize.getHeight()/8));
            numberJLabel.setValue(i);

            numberJLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    MyJLabel executor = (MyJLabel) e.getSource();

                    if (!task.isEmpty()) {
                        String last=task.substring(task.length()-1, task.length());
                        char lastChar = task.charAt(task.length() - 1);
                        if (Character.isDigit(lastChar)) {
                            task = task.substring(0, task.length() - 1);
                            int number = Integer.parseInt(last) + executor.getValue();
                            task += number;
                        } else {
                            task += executor.getValue();
                        }
                    } else {
                        task += executor.getValue();
                    }
                    updateTaskArea();
                }
            });

            numberButtonsContainer.add(numberJLabel);
        }
        buttonContainer.add(numberButtonsContainer);

        JPanel operatonButtonsContainer = new JPanel(new GridLayout(1, 5));
        operatonButtonsContainer.setOpaque(false);
        
        Image image = new ImageIcon("images/ButtonBez.png").getImage();
        JLabel buttonBez = new JLabel(ScaleImage.getScaledImageIcon(image, FrameSize.getWidth()/13, FrameSize.getHeight()/8));
        buttonBez.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StringBuilder stringbuilder=new StringBuilder(task);
                stringbuilder.append(" bez ");
                task=stringbuilder.toString();
                updateTaskArea();
            }
        });
        operatonButtonsContainer.add(buttonBez);

        image = new ImageIcon("images/ButtonI.png").getImage();
        JLabel buttonI = new JLabel(ScaleImage.getScaledImageIcon(image, FrameSize.getWidth()/13, FrameSize.getHeight()/8));
        buttonI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StringBuilder stringbuilder=new StringBuilder(task);
                stringbuilder.append(" i ");
                task=stringbuilder.toString();
                updateTaskArea();
            }
        });
        operatonButtonsContainer.add(buttonI);

        image = new ImageIcon("images/ButtonPo.png").getImage();
        JLabel buttonPo = new JLabel(ScaleImage.getScaledImageIcon(image, FrameSize.getWidth()/13, FrameSize.getHeight()/8));
        buttonPo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StringBuilder stringbuilder=new StringBuilder(task);
                stringbuilder.append(" po ");
                task=stringbuilder.toString();
                updateTaskArea();
            }
        });
        operatonButtonsContainer.add(buttonPo);

        image = new ImageIcon("images/ButtonPrzez.png").getImage();
        JLabel buttonPrzez = new JLabel(ScaleImage.getScaledImageIcon(image, FrameSize.getWidth()/10, FrameSize.getHeight()/6));
        buttonPrzez.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StringBuilder stringbuilder=new StringBuilder(task);
                stringbuilder.append(" przez ");
                task=stringbuilder.toString();
                updateTaskArea();
            }
        });
        operatonButtonsContainer.add(buttonPrzez);

        image = new ImageIcon("images/ButtonMam.png").getImage();
        JLabel buttonMam = new JLabel(ScaleImage.getScaledImageIcon(image, FrameSize.getWidth()/13, FrameSize.getHeight()/8));
        buttonMam.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(task);
                double solution = new ONP().getSolution(task);
                System.out.println(solution);
                parent.setSolution(solution);
                parent.hideCreateTaskJPanel();
                parent.showResultPanel();
                
                db.newTask(task, solution);
                          
                db.closeConnection();
            }
        });
        operatonButtonsContainer.add(buttonMam);

        buttonContainer.add(operatonButtonsContainer);

        bgComponent.add(buttonContainer, BorderLayout.SOUTH);

        add(bgComponent);
        
        
    }
    
    public void updateTaskArea() {

        this.taskTextArea.setText(task);
        parent.repaint();
    }
}
