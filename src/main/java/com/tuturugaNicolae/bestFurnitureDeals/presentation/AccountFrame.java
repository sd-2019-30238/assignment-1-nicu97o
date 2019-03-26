package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller.UserController;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFrame extends JFrame {
    private JTextField actualUserTypeField;
    private JTextField actualIdField;
    private JTextField actualMailField;
    private JTextField actualUsernameField;
    private JButton updateButton;
    private JButton backButton;
    private JTextField newMailField;
    private JTextField newPasswordField;
    private JButton deleteButton;
    private JPanel panel;
    private JTextField mesageField;

    /**
     * Other frames
     */
    private JFrame parentFrame;
    private JFrame loginFrame;

    /**
     * Current frame variables
     */
    private ApplicationContext applicationContext;
    private UserController userController;
    private SecurityContext securityContext;

    public AccountFrame(ApplicationContext applicationContext, JFrame parentFrame, JFrame loginFrame) {
        this.applicationContext = applicationContext;
        this.loginFrame = loginFrame;
        this.userController = applicationContext.getBean("userController", UserController.class);
        this.securityContext = applicationContext.getBean("securityContext", SecurityContext.class);
        this.parentFrame = parentFrame;
        this.backButton.addActionListener(new AccountListener());
        this.updateButton.addActionListener(new AccountListener());
        this.deleteButton.addActionListener(new AccountListener());
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1920, 1080);
        setVisible(false);
    }

    public void initTextFields() {
        UserDTO userDTO = userController.getUserByUsername(securityContext.getNameOfAuthenticatedUser());
        actualIdField.setText(userDTO.getId().toString());
        actualUsernameField.setText(userDTO.getUsername());
        actualMailField.setText(userDTO.getMail());
        actualUserTypeField.setText(userDTO.getUserTypeDTO().toString());
    }

    private class AccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                parentFrame.setVisible(true);
                setVisible(false);
            } else if (e.getSource() == updateButton) {
                try {
                    userController.updateUser(new UserDTO(Long.parseLong(actualIdField.getText()), actualUsernameField.getText(), newPasswordField.getText(), newMailField.getText(), UserTypeDTO.valueOf(actualUserTypeField.getText())), securityContext.getNameOfAuthenticatedUser());
                    initTextFields();
                    mesageField.setText("Updated");
                } catch (Exception exp) {
                    mesageField.setText(exp.getMessage());
                }
            } else if (e.getSource() == deleteButton) {
                try {
                    userController.deleteUser();
                    loginFrame.setVisible(true);
                    setVisible(false);
                } catch (Exception exp) {
                    mesageField.setText(exp.getMessage());
                }
            }
        }
    }
}
