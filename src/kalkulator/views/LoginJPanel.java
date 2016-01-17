package kalkulator.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import kalkulator.controller.LoginController;
import kalkulator.models.DefaultOptions;
import kalkulator.models.User;

/**
 * Klasa reprezentująca panel logowania
 *
 * @author Karol
 */
public class LoginJPanel extends JPanel
{

    private final User user;
    private final DefaultOptions defaultOptions;

    private JTextField username;
    private JPasswordField password;
    private JButton okButton;
    private JButton guestSessionButton;
    private final Frame parent;

    /**
     *
     * @param _defaultOptions obiekt domyślnych opci
     */
    public LoginJPanel(Frame parent, DefaultOptions defaultOptions, User user)
    {
        this.defaultOptions = defaultOptions;
        this.user=user;
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
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(2, 2));
        containerPanel.add(new JLabel("Nazwa użytkownika:"));
        username = new JTextField(defaultOptions.getDefaultUsername());
        containerPanel.add(username);
        containerPanel.add(new JLabel("wprowadż hasło:"));
        password = new JPasswordField("");
        containerPanel.add(password);
        

        add(containerPanel, BorderLayout.NORTH);

        okButton = new JButton("zatwierdź");
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if (!user.isLoggedIn()) {
                    
                    LoginController loginController= new LoginController();
                    if(loginController.logIn(username.getText(), password.getPassword().toString()))
                    {
                        parent.hideLoginPanel();
                        parent.showCreateTaskJPanel();
                    }
                }
            }
        });

        guestSessionButton = new JButton("policz jako gość");
        guestSessionButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                parent.hideLoginPanel();
                parent.showCreateTaskJPanel();
            }
        });

        JPanel buttonContainerPanel = new JPanel();
        buttonContainerPanel.add(okButton);
        buttonContainerPanel.add(guestSessionButton);
        add(buttonContainerPanel, BorderLayout.SOUTH);
    }

}
