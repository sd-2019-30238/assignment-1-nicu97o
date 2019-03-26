package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    /**
     * Current frame components
     */
    private JButton submit;
    private JPanel panel1;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton registerButton;
    private JTextField messagesTextField;

    /**
     * Other frames
     */
    private RegisterFrame registerFrame;
    private MainMenuFrame mainMenuFrame;

    /**
     * Current frame variables
     */
    private SecurityContext securityContext;

    public LoginFrame(ApplicationContext applicationContext) {
        initializeCurrentFrame(applicationContext);
        registerFrame = new RegisterFrame(applicationContext, this);
        mainMenuFrame = new MainMenuFrame(applicationContext, this);
    }

    private void initializeCurrentFrame(ApplicationContext applicationContext) {
        this.securityContext = applicationContext.getBean("securityContext", SecurityContext.class);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1920, 1080);
        submit.addActionListener(new LoginFrameListener());
        registerButton.addActionListener(new LoginFrameListener());
        setVisible(true);
    }

    private class LoginFrameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                try {
                    securityContext.authenticate(usernameTextField.getText(), passwordTextField.getText());
                    mainMenuFrame.setVisible(true);
                    setVisible(false);
                } catch (Exception exp) {
                    messagesTextField.setText(exp.getMessage());
                }
            } else if (e.getSource() == registerButton) {
                registerFrame.setVisible(true);
                setVisible(false);
            }
        }
    }
}
