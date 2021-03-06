package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.transactionScript.OrderTS;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.ClientOrderDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FeedbackMessageDTO;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyOrdersFrame extends JFrame {
    private JList clientOrders;
    private JTextField orderHistoryField;
    private JTextField feedbackTitleField;
    private JTextField feedbackMessageField;
    private JButton postFeedbackButton;
    private JButton backButton;
    private JButton getOrderHistoryButton;
    private JPanel panel;

    /**
     * Other frames
     */
    private JFrame parentFrame;

    private OrderTS orderTS;

    public MyOrdersFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.orderTS = applicationContext.getBean("orderTS", OrderTS.class);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1920, 1080);
        this.backButton.addActionListener(new MyOrdersListener());
        this.getOrderHistoryButton.addActionListener(new MyOrdersListener());
        this.postFeedbackButton.addActionListener(new MyOrdersListener());
        setVisible(false);
    }

    public void initializeOrdersList() {
        try {
            clientOrders.setListData(orderTS.getFinishedOrdersForLoggedUser().toArray());
        } catch (Exception e) {
            clientOrders.setListData(new ArrayList<ClientOrderDTO>().toArray());
            e.printStackTrace();
        }
    }

    private class MyOrdersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                parentFrame.setVisible(true);
                setVisible(false);
            } else if (e.getSource() == getOrderHistoryButton) {
                try {
                    ClientOrderDTO clientOrderDTO = (ClientOrderDTO) clientOrders.getSelectedValue();
                    orderHistoryField.setText(orderTS.getOrderHistoryForAClientOrder(clientOrderDTO).toString());
                } catch (Exception exp) {
                    orderHistoryField.setText(exp.getMessage());
                }
            } else if (e.getSource() == postFeedbackButton) {
                try {
                    ClientOrderDTO clientOrderDTO = (ClientOrderDTO) clientOrders.getSelectedValue();
                    FeedbackMessageDTO feedbackMessageDTO = new FeedbackMessageDTO();
                    feedbackMessageDTO.setMessageBody(feedbackMessageField.getText());
                    feedbackMessageDTO.setTitle(feedbackTitleField.getText());
                    orderTS.postFeedbackMessage(feedbackMessageDTO, orderTS.getOrderHistoryForAClientOrder(clientOrderDTO));
                    orderHistoryField.setText("Successful");
                } catch (Exception exp) {
                    orderHistoryField.setText(exp.getMessage());
                    exp.printStackTrace();
                }
            }
        }
    }
}
