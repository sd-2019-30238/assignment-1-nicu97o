package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller.CartController;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.BoughtFurniture;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CartFrame extends JFrame {
    private JPanel panel;
    private JList productsList;
    private JButton backButton;
    private JButton checkoutButton;
    private JButton deleteProductButton;
    private JTextField messageField;

    /**
     * Other frames
     */
    private JFrame parentFrame;

    /**
     * Current frame variables
     */
    private ApplicationContext applicationContext;
    private CartController cartController;

    public CartFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.applicationContext = applicationContext;
        cartController = applicationContext.getBean("cartController", CartController.class);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1920, 1080);
        backButton.addActionListener(new CartListener());
        checkoutButton.addActionListener(new CartListener());
        deleteProductButton.addActionListener(new CartListener());
        setVisible(false);
    }

    public void initializeList() {
        try {
            productsList.setListData(cartController.getAllProductsFromCart().toArray());
        } catch (Exception e) {
            productsList.setListData(new ArrayList<BoughtFurnitureDTO>().toArray());
        }
    }

    private class CartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                parentFrame.setVisible(true);
                setVisible(false);
            } else if (e.getSource() == checkoutButton) {
                try {
                    cartController.checkout();
                    messageField.setText("Successful");
                    initializeList();
                } catch (Exception exp) {
                    messageField.setText(exp.getMessage());
                }
            } else if (e.getSource() == deleteProductButton) {
                try {
                    BoughtFurnitureDTO boughtFurnitureDTO = (BoughtFurnitureDTO) productsList.getSelectedValue();
                    cartController.deleteProductFromCart(boughtFurnitureDTO);
                    messageField.setText("Deleted");
                    initializeList();
                } catch (Exception exp) {
                    messageField.setText(exp.getMessage());
                }
            }
        }
    }
}
