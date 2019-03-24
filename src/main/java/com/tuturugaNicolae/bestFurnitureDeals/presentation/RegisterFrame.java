package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    /**
     * Current frame components
     */
    private JTextField usernameField;
    private JTextField mailField;
    private JPasswordField passwordField;
    private JPanel panel;
    private JButton submitButton;
    private JRadioButton staffRadioButton;
    private JRadioButton clientRadioButton;
    private JButton backButton;
    private JTextField resultMessage;
    private ButtonGroup buttonGroup;

    /**
     * Parent frame components
     */
    private JFrame parentFrame;

    /**
     * Current frame variables
     */
    private UserService userService;

    public RegisterFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        userService = applicationContext.getBean("userServiceImpl", UserService.class);
        setContentPane(panel);
        buttonGroup = new ButtonGroup();
        staffRadioButton.setActionCommand(UserTypeDTO.STAFF.toString());
        buttonGroup.add(staffRadioButton);
        clientRadioButton.setActionCommand(UserTypeDTO.CLIENT.toString());
        buttonGroup.add(clientRadioButton);
        submitButton.addActionListener(new RegisterFrameListener());
        backButton.addActionListener(new RegisterFrameListener());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1920, 1080);
        setVisible(false);
    }

    private class RegisterFrameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitButton) {
                UserDTO userDTO = new UserDTO(usernameField.getText(), mailField.getText(), UserTypeDTO.valueOf(buttonGroup.getSelection().getActionCommand()));
                try {
                    userService.addUser(userDTO, passwordField.getText());
                    resultMessage.setText("Success");
                } catch (Exception exp) {
                    resultMessage.setText(exp.getMessage());
                }
            } else if (e.getSource() == backButton) {
                setVisible(false);
                parentFrame.setVisible(true);
            }
        }
    }
}
