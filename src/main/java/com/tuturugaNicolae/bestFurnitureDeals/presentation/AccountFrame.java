package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFrame extends JFrame {
    /**
     * Current frame components
     */
    private JPanel panel;
    private JButton logoutButton;
    private JButton productsMenuButton;

    /**
     * Parent frame components
     */
    private JFrame parentFrame;
    private JFrame productsFrame;

    /**
     * Current frame components
     */
    private SecurityContext securityContext;
    private UserService userService;

    public AccountFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        initializeCurrentFrame(applicationContext, parentFrame);
        productsFrame = new ProductMenuFrame(applicationContext, this);
    }

    private void initializeCurrentFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        this.securityContext = applicationContext.getBean("securityContext", SecurityContext.class);
        this.userService = applicationContext.getBean("userServiceImpl", UserService.class);
        this.parentFrame = parentFrame;
        setContentPane(panel);
        logoutButton.addActionListener(new AccountFrameListener());
        productsMenuButton.addActionListener(new AccountFrameListener());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1920, 1080);
        setVisible(false);
    }

    private class AccountFrameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logoutButton) {
                parentFrame.setVisible(true);
                setVisible(false);
                securityContext.logout();
            } else if (e.getSource() == productsMenuButton) {
                productsFrame.setVisible(true);
                setVisible(false);
            }
        }
    }
}