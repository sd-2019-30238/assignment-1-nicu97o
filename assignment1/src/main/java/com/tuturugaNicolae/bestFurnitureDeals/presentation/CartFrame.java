package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.transactionScript.CartTS;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.transactionScript.OrderTS;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.BoughtFurnitureDTO;
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
    private JTextField currentOrder;

    /**
     * Other frames
     */
    private JFrame parentFrame;

    private CartTS cartTS;
    private OrderTS orderTS;

    public CartFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.cartTS = applicationContext.getBean("cartTS", CartTS.class);
        this.orderTS = applicationContext.getBean("orderTS", OrderTS.class);
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
            productsList.setListData(cartTS.getAllProductsFromCart().toArray());
        } catch (Exception e) {
            productsList.setListData(new ArrayList<BoughtFurnitureDTO>().toArray());
        }
    }

    public void initializeCurrentOrderField() {
        try {
            currentOrder.setText(orderTS.getCurrentClientOrder().toString());
        } catch (Exception e) {
            currentOrder.setText(e.getMessage());
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
                    cartTS.checkout();
                    messageField.setText("Successful");
                    initializeList();
                } catch (Exception exp) {
                    messageField.setText(exp.getMessage());
                }
                initializeCurrentOrderField();
            } else if (e.getSource() == deleteProductButton) {
                try {
                    BoughtFurnitureDTO boughtFurnitureDTO = (BoughtFurnitureDTO) productsList.getSelectedValue();
                    cartTS.deleteProductFromCart(boughtFurnitureDTO);
                    messageField.setText("Deleted");
                    initializeList();
                } catch (Exception exp) {
                    messageField.setText(exp.getMessage());
                }
                initializeCurrentOrderField();
            }
        }
    }
}
