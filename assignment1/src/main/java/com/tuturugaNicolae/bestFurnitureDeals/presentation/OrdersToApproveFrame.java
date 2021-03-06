package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.transactionScript.OrderTS;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrdersToApproveFrame extends JFrame {
    private JPanel panel;
    private JList ordersList;
    private JButton backButton;
    private JButton getAllOrdersButton;
    private JButton getUnfinishedOrdersButton;
    private JButton getOrderHistoryButton;
    private JButton approveOrderButton;
    private JButton updateOrderStateButton;
    private JTextArea messageArea;
    private JButton getOrdersFeedbackButton;

    /**
     * Other frames
     */
    private JFrame parentFrame;

    private OrderTS orderTS;

    public OrdersToApproveFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.orderTS = applicationContext.getBean("orderTS", OrderTS.class);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1920, 1080);
        this.backButton.addActionListener(new OrdersToApproveListener());
        this.getAllOrdersButton.addActionListener(new OrdersToApproveListener());
        this.getUnfinishedOrdersButton.addActionListener(new OrdersToApproveListener());
        this.getOrderHistoryButton.addActionListener(new OrdersToApproveListener());
        this.approveOrderButton.addActionListener(new OrdersToApproveListener());
        this.updateOrderStateButton.addActionListener(new OrdersToApproveListener());
        this.getOrdersFeedbackButton.addActionListener(new OrdersToApproveListener());
        setVisible(false);
    }

    public void initializeOrderList() {
        try {
            ordersList.setListData(orderTS.getAllOrders().toArray());
        } catch (Exception exp) {
            ordersList.setListData(new ArrayList<ClientOrderDTO>().toArray());
            messageArea.setText(exp.getMessage());
        }
    }

    private class OrdersToApproveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                ordersList.setListData(new ArrayList<ClientOrderDTO>().toArray());
                parentFrame.setVisible(true);
                setVisible(false);
            } else if (e.getSource() == getAllOrdersButton) {
                initializeOrderList();
            } else if (e.getSource() == getUnfinishedOrdersButton) {
                try {
                    ordersList.setListData(orderTS.getAllFinishedOrders().toArray());
                } catch (Exception exp) {
                    ordersList.setListData(new ArrayList<ClientOrderDTO>().toArray());
                    messageArea.setText(exp.getMessage());
                }
            } else if (e.getSource() == getOrderHistoryButton) {
                try {
                    ClientOrderDTO clientOrderDTO = (ClientOrderDTO) ordersList.getSelectedValue();
                    messageArea.setText(orderTS.getOrderHistoryForAClientOrder(clientOrderDTO).toString());
                } catch (Exception exp) {
                    messageArea.setText(exp.getMessage());
                    exp.printStackTrace();
                }
            } else if (e.getSource() == approveOrderButton) {
                try {
                    ClientOrderDTO clientOrderDTO = (ClientOrderDTO) ordersList.getSelectedValue();
                    orderTS.approveClientOrder(clientOrderDTO);
                    messageArea.setText("Successful");
                } catch (Exception exp) {
                    messageArea.setText(exp.getMessage());
                    exp.printStackTrace();
                }
                initializeOrderList();
            } else if (e.getSource() == updateOrderStateButton) {
                try {
                    ClientOrderDTO clientOrderDTO = (ClientOrderDTO) ordersList.getSelectedValue();
                    orderTS.updateOrderState(clientOrderDTO);
                } catch (Exception exp) {
                    messageArea.setText(exp.getMessage());
                    exp.printStackTrace();
                }
            } else if (e.getSource() == getOrdersFeedbackButton) {
                try {
                    ClientOrderDTO clientOrderDTO = (ClientOrderDTO) ordersList.getSelectedValue();
                    messageArea.setText(orderTS.getFeedbackMessageByClientOrder(clientOrderDTO).toString());
                } catch (Exception exp) {
                    messageArea.setText(exp.getMessage());
                }
            }
        }
    }
}
