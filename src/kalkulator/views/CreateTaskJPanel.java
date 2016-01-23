package kalkulator.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import kalkulator.models.DataBase;
import kalkulator.models.ONP;
import kalkulator.models.ScaleImage;

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

        String[] tasks = (String[]) DataBase.getExistingTasts();
        JComboBox existingTasks = new JComboBox(tasks);

        JPanel existingTasksContainer = new JPanel(new GridLayout(1, 2));
        existingTasksContainer.setOpaque(false);
        existingTasksContainer.add(chooseExistingLabel);
        existingTasksContainer.add(existingTasks);
        bgComponent.add(existingTasksContainer, BorderLayout.NORTH);

        taskTextArea = new JTextArea("Wprowadź zadanie");
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
            MyJLabel numberJLabel = new MyJLabel(ScaleImage.getScaledImageIcon(image, 80, 80));
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

        JLabel buttonBez = new JLabel(new ImageIcon("images/buttonBez.png"));
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

        JLabel buttonI = new JLabel(new ImageIcon("images/buttonI.png"));
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

        JLabel buttonPo = new JLabel(new ImageIcon("images/buttonPo.png"));
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

        JLabel buttonPrzez = new JLabel(new ImageIcon("images/buttonPrzez.png"));
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

        JLabel buttonMam = new JLabel(new ImageIcon("images/buttonMam.png"));
        buttonMam.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(task);
                double solution = new ONP().getSolution(task);
                System.out.println(solution);
                parent.setSolution(solution);
                parent.hideCreateTaskJPanel();
                parent.showResultPanel();
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
