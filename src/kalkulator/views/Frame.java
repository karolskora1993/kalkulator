package kalkulator.views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import kalkulator.models.DefaultOptions;
import kalkulator.models.User;

public class Frame extends JFrame {

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar;
    private JPanel loginPanel;
    private JPanel createTaskPanel = new CreateTaskJPanel(this);
    private JPanel resultPanel;
    private double solution;

    public Frame(DefaultOptions defaultOptions, User user) {
        initComponents(defaultOptions, user);
        showLoginPanel();
    }

    public void setSolution(double sol) {
        this.solution = sol;
    }
    
    public double getSolution() {
        return this.solution;
    }

    private void initComponents(DefaultOptions defaultOptions, User user) {

        setSize((int) FrameSize.getWidth(), (int) FrameSize.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kalkulator dla osób nieznających matematyki");
        setLocationByPlatform(true);
        setResizable(false);

        loginPanel = new LoginJPanel(this, defaultOptions, user);

        jMenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jMenu1.setText("opcje zadania");
        jMenuBar.add(jMenu1);

        jMenu2.setText("opcje konta");
        jMenuBar.add(jMenu2);

        jMenu3.setText("opcje administratora");
        jMenuBar.add(jMenu3);

        jMenu4.setText("pomoc");
        jMenuBar.add(jMenu4);

        setJMenuBar(jMenuBar);

    }

    public void showLoginPanel() {
        add(loginPanel);
        loginPanel.setVisible(true);
    }

    public void showCreateTaskJPanel() {
        add(createTaskPanel);
        createTaskPanel.setVisible(true);
    }

    public void hideLoginPanel() {
        loginPanel.setVisible(false);
    }

    public void hideCreateTaskJPanel() {
        createTaskPanel.setVisible(false);
    }

    public void showResultPanel() {
        resultPanel = new ResultPanel(this);
        add(resultPanel);
        resultPanel.setVisible(true);
    }
}
