package kalkulator.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import kalkulator.controller.LoginController;
import kalkulator.models.DefaultOptions;
import kalkulator.models.ScaleImage;
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
    
    private BackgroundComponent bgComponent;
    private JTextField username;
    private JPasswordField password;
    private JButton okButton;
    private JButton guestSessionButton;
    private final Frame parent;

    /**
     *
     * @param defaultOptions obiekt domyślnych opcji
     * @param user obiekt użytkownika
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
        
        bgComponent=new BackgroundComponent("loginBG.jpg");
        bgComponent.setLayout(new BorderLayout());
       
        JPanel containerPanel = new JPanel();
        containerPanel.setOpaque(false);
        containerPanel.setLayout(new GridLayout(2, 2));
        JLabel name=new JLabel("Nazwa użytkownika:");
        name.setForeground(Color.WHITE);
        containerPanel.add(name);
        username = new JTextField(defaultOptions.getDefaultUsername());
        containerPanel.add(username);
        
        JLabel passwdLabel=new JLabel("wprowadż hasło:");
        passwdLabel.setForeground(Color.WHITE);
        containerPanel.add(passwdLabel);
        password = new JPasswordField("");
        containerPanel.add(password);
        
        bgComponent.add(containerPanel, BorderLayout.NORTH);

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
        buttonContainerPanel.setOpaque(false);
        buttonContainerPanel.add(okButton);
        buttonContainerPanel.add(guestSessionButton);
        bgComponent.add(buttonContainerPanel, BorderLayout.SOUTH);
        add(bgComponent);
        
       
    }

}

