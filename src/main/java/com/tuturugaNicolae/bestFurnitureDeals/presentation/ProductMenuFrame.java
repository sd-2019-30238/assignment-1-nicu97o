package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller.CartController;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller.DealController;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller.FurnitureController;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.controller.UserController;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductMenuFrame extends JFrame {
    private JPanel panel;
    private JList furnitureList;
    private JTextField nameField;
    private JTextField descriptionField;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField messageField;
    private JList dealsList;
    private JTextField dealNameField;
    private JRadioButton EACH_PRODUCT_REDUCTION_5_PERCENTRadioButton;
    private JCheckBox availableCheckBox;
    private JButton updateButton1;
    private JButton deleteButton1;
    private JButton insertButton1;
    private JRadioButton EACH_PRODUCT_REDUCTION_25_PERCENTRadioButton;
    private JRadioButton CHECKOUT_5_PERCENTRadioButton;
    private JRadioButton CHECKOUT_25_PERCENTRadioButton;
    private JTextField dealPriceField;
    private JTextField dealQuantityField;
    private JTextField messageDeals;
    private JTextField quantityToBuyField;
    private JButton addToCartButton;
    private JTextField addToCartMessage;
    private JButton filtterByTypeButton;
    private JButton filtterByNameButton;
    private JButton filtterByPriceButton;
    private JTextField nameToSearchFor;
    private JTextField minPrice;
    private JTextField maxPrice;
    private JButton getAllDealsButton;
    private JButton backButton;
    private ButtonGroup buttonGroup;

    /**
     * Other frames
     */
    private JFrame parentFrame;

    /**
     * Current frame varibales
     */
    private FurnitureController furnitureController;
    private DealController dealController;
    private CartController cartController;
    private SecurityContext securityContext;
    private UserController userController;

    public ProductMenuFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        initializeCurrentFrame(applicationContext, parentFrame);
    }

    private void initializeCurrentFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        this.furnitureController = applicationContext.getBean("furnitureController", FurnitureController.class);
        this.dealController = applicationContext.getBean("dealController", DealController.class);
        this.cartController = applicationContext.getBean("cartController", CartController.class);
        this.securityContext = applicationContext.getBean("securityContext", SecurityContext.class);
        this.userController = applicationContext.getBean("userController", UserController.class);
        this.parentFrame = parentFrame;
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insertButton.addActionListener(new FurnitureMenuListener());
        updateButton.addActionListener(new FurnitureMenuListener());
        deleteButton.addActionListener(new FurnitureMenuListener());
        buttonGroup = new ButtonGroup();
        EACH_PRODUCT_REDUCTION_5_PERCENTRadioButton.setActionCommand(DealTypeDTO.EACH_PRODUCT_REDUCTION_5_PERCENT.toString());
        buttonGroup.add(EACH_PRODUCT_REDUCTION_5_PERCENTRadioButton);
        EACH_PRODUCT_REDUCTION_25_PERCENTRadioButton.setActionCommand(DealTypeDTO.THREE_WITH_REDUCTION_25_PERCENT.toString());
        buttonGroup.add(EACH_PRODUCT_REDUCTION_25_PERCENTRadioButton);
        CHECKOUT_5_PERCENTRadioButton.setActionCommand(DealTypeDTO.NORMAL.toString());
        buttonGroup.add(CHECKOUT_5_PERCENTRadioButton);
        CHECKOUT_25_PERCENTRadioButton.setActionCommand(DealTypeDTO.PAY_ONE_AND_TAKE_SECOND_FREE.toString());
        buttonGroup.add(CHECKOUT_25_PERCENTRadioButton);
        insertButton1.addActionListener(new DealMenuListener());
        updateButton1.addActionListener(new DealMenuListener());
        deleteButton1.addActionListener(new DealMenuListener());
        addToCartButton.addActionListener(new BoughtProductListener());
        filtterByNameButton.addActionListener(new DealMenuListener());
        filtterByPriceButton.addActionListener(new DealMenuListener());
        filtterByTypeButton.addActionListener(new DealMenuListener());
        getAllDealsButton.addActionListener(new DealMenuListener());
        backButton.addActionListener(e -> {
            parentFrame.setVisible(true);
            setVisible(false);
        });
        pack();
        setSize(1920, 1080);
        setVisible(false);
        buildFurnitureList();
        buildDealsList();
    }

    public void buildFurnitureList() {
        try {
            furnitureList.setListData(furnitureController.getAllFurniture().toArray());
        } catch (Exception e) {
            furnitureList.setListData(new ArrayList<FurnitureDTO>().toArray());
        }
    }

    public void buildDealsList() {
        try {
            dealsList.setListData(dealController.getAllDeals().toArray());
        } catch (Exception e) {
            dealsList.setListData(new ArrayList<DealDTO>().toArray());
        }
    }

    private class FurnitureMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == insertButton) {
                try {
                    FurnitureDTO furnitureDTO = new FurnitureDTO(0L, nameField.getText(), descriptionField.getText());
                    furnitureController.addFurniture(furnitureDTO);
                    messageField.setText("Inserted");
                } catch (Exception exp) {
                    messageField.setText(exp.getMessage());
                }
                buildFurnitureList();
            } else if (e.getSource() == updateButton) {
                try {
                    FurnitureDTO oldFurnitureDTO = (FurnitureDTO) furnitureList.getSelectedValue();
                    FurnitureDTO furnitureDTO = new FurnitureDTO(oldFurnitureDTO.getId(), nameField.getText(), descriptionField.getText());
                    furnitureController.updateFurniture(furnitureDTO);
                    messageField.setText("Updated");
                } catch (Exception exp) {
                    messageField.setText(exp.getMessage());
                }
                buildFurnitureList();
            } else if (e.getSource() == deleteButton) {
                try {
                    FurnitureDTO oldFurnitureDTO = (FurnitureDTO) furnitureList.getSelectedValue();
                    furnitureController.deleteFurniture(oldFurnitureDTO);
                    messageField.setText("Deleted");
                } catch (Exception exp) {
                    messageField.setText(exp.getMessage());
                }
                buildFurnitureList();
            }
        }
    }

    private class DealMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == insertButton1) {
                try {
                    DealDTO dealDTO = new DealDTO(0L, dealNameField.getText(), DealTypeDTO.valueOf(buttonGroup.getSelection().getActionCommand().toString()),
                            (FurnitureDTO) furnitureList.getSelectedValue(), BigDecimal.valueOf(Double.parseDouble(dealPriceField.getText())),
                            availableCheckBox.isSelected(), Integer.parseInt(dealQuantityField.getText()));
                    dealController.addDeal(dealDTO);
                    messageDeals.setText("Added");
                } catch (Exception exp) {
                    messageDeals.setText(exp.getMessage());
                    exp.printStackTrace();
                }
                buildDealsList();
            } else if (e.getSource() == updateButton1) {
                try {
                    DealDTO oldDeal = (DealDTO) dealsList.getSelectedValue();
                    oldDeal.setAvailable(availableCheckBox.isSelected());
                    oldDeal.setAvailableQuantity(Integer.parseInt(dealQuantityField.getText()));
                    oldDeal.setPrice(BigDecimal.valueOf(Double.parseDouble(dealPriceField.getText())));
                    oldDeal.setDealTypeDTO(DealTypeDTO.valueOf(buttonGroup.getSelection().getActionCommand()));
                    dealController.updateDeal(oldDeal);
                    messageField.setText("Updated");
                } catch (Exception exp) {
                    messageDeals.setText(exp.getMessage());
                }
                buildDealsList();
            } else if (e.getSource() == deleteButton1) {
                try {
                    DealDTO oldDeal = (DealDTO) dealsList.getSelectedValue();
                    dealController.deleteDeal(oldDeal);
                    messageField.setText("Deleted");
                } catch (Exception exp) {
                    messageDeals.setText(exp.getMessage());
                }
                buildDealsList();
            } else if (e.getSource() == filtterByNameButton) {
                try {
                    dealsList.setListData(dealController.getDealsByName(nameToSearchFor.getText()).toArray());
                    messageField.setText("Successful!");
                } catch (Exception exp) {
                    messageDeals.setText(exp.getMessage());
                    dealsList.setListData(new ArrayList<DealDTO>().toArray());
                }
            } else if (e.getSource() == filtterByPriceButton) {
                try {
                    dealsList.setListData(dealController.getDealsByPrice(BigDecimal.valueOf(Double.parseDouble(minPrice.getText())), BigDecimal.valueOf(Double.parseDouble(maxPrice.getText()))).toArray());
                    messageField.setText("successfull");
                } catch (Exception exp) {
                    messageDeals.setText(exp.getMessage());
                    dealsList.setListData(new ArrayList<DealDTO>().toArray());
                }
            } else if (e.getSource() == filtterByTypeButton) {
                try {
                    dealsList.setListData(dealController.getDealsByType(DealTypeDTO.valueOf(buttonGroup.getSelection().getActionCommand())).toArray());
                    messageField.setText("successfull");
                } catch (Exception exp) {
                    messageDeals.setText(exp.getMessage());
                    dealsList.setListData(new ArrayList<DealDTO>().toArray());
                }
            } else if (e.getSource() == getAllDealsButton) {
                buildDealsList();
            }
        }
    }

    private class BoughtProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addToCartButton) {
                try {
                    DealDTO dealDTO = (DealDTO) dealsList.getSelectedValue();
                    cartController.addProductToCart(dealDTO, Integer.parseInt(quantityToBuyField.getText()));
                    addToCartMessage.setText("Added!");
                } catch (Exception exp) {
                    exp.printStackTrace();
                    addToCartMessage.setText(exp.getMessage());
                }
                buildDealsList();
            }
        }
    }
}
