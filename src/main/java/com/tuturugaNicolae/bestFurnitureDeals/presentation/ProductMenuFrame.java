package com.tuturugaNicolae.bestFurnitureDeals.presentation;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.converter.Converter;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.converter.impl.FurnitureConverter;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.DealTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.FurnitureDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.DealService;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.FurnitureService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton acceptButton1;
    private JRadioButton EACH_PRODUCT_REDUCTION_25_PERCENTRadioButton;
    private JRadioButton CHECKOUT_5_PERCENTRadioButton;
    private JRadioButton CHECKOUT_25_PERCENTRadioButton;
    private JTextField dealPriceField;
    private JTextField dealQuantityField;
    private ButtonGroup buttonGroup;

    /**
     * Other frames
     */
    private JFrame parentFrame;

    /**
     * Current frame varibales
     */
    private FurnitureService furnitureService;
    private Converter<FurnitureDTO> furnitureDTOConverter;
    private DealService dealService;

    public ProductMenuFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        initializeCurrentFrame(applicationContext, parentFrame);
    }

    private void initializeCurrentFrame(ApplicationContext applicationContext, JFrame parentFrame) {
        this.furnitureService = applicationContext.getBean("furnitureServiceImpl", FurnitureService.class);
        this.furnitureDTOConverter = applicationContext.getBean("furnitureConverter", FurnitureConverter.class);
        this.dealService = applicationContext.getBean("dealServiceImpl", DealService.class);
        this.parentFrame = parentFrame;
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insertButton.addActionListener(new FurnitureMenuListener());
        updateButton.addActionListener(new FurnitureMenuListener());
        deleteButton.addActionListener(new FurnitureMenuListener());
        buttonGroup = new ButtonGroup();
        EACH_PRODUCT_REDUCTION_5_PERCENTRadioButton.setActionCommand(DealTypeDTO.EACH_PRODUCT_REDUCTION_5_PERCENT.toString());
        buttonGroup.add(EACH_PRODUCT_REDUCTION_5_PERCENTRadioButton);
        EACH_PRODUCT_REDUCTION_25_PERCENTRadioButton.setActionCommand(DealTypeDTO.EACH_PRODUCT_REDUCTION_25_PERCENT.toString());
        buttonGroup.add(EACH_PRODUCT_REDUCTION_25_PERCENTRadioButton);
        CHECKOUT_5_PERCENTRadioButton.setActionCommand(DealTypeDTO.CHECKOUT_5_PERCENT.toString());
        buttonGroup.add(CHECKOUT_5_PERCENTRadioButton);
        CHECKOUT_25_PERCENTRadioButton.setActionCommand(DealTypeDTO.CHECKOUT_25_PERCENT.toString());
        buttonGroup.add(CHECKOUT_25_PERCENTRadioButton);
        insertButton1.addActionListener(new DealMenuListener());
        updateButton1.addActionListener(new DealMenuListener());
        deleteButton1.addActionListener(new DealMenuListener());
        acceptButton1.addActionListener(new DealMenuListener());
        pack();
        setSize(1920, 1080);
        setVisible(false);
        buildFurnitureList();
    }

    private void buildFurnitureList() {
        try {
            furnitureList.setListData(furnitureService.getAllFurniture().toArray());
        } catch (Exception e) {
            furnitureList.setListData(new ArrayList<FurnitureDTO>().toArray());
        }
    }

    private void buildDealsList() {
        try {
            dealsList.setListData(dealService.getAllDeals().toArray());
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
                    furnitureService.addFurniture(furnitureDTO);
                    messageField.setText("Inserted");
                } catch (Exception exp) {
                    messageField.setText(exp.getMessage());
                }
                buildFurnitureList();
            } else if (e.getSource() == updateButton) {
                try {
                    FurnitureDTO oldFurnitureDTO = furnitureDTOConverter.convertToDTO(furnitureList.getSelectedValue().toString());
                    FurnitureDTO furnitureDTO = new FurnitureDTO(oldFurnitureDTO.getId(), nameField.getText(), descriptionField.getText());
                    furnitureService.updateFurniture(furnitureDTO);
                    messageField.setText("Updated");
                } catch (Exception exp) {
                    messageField.setText(exp.getMessage());
                }
                buildFurnitureList();
            } else if (e.getSource() == deleteButton) {
                try {
                    FurnitureDTO oldFurnitureDTO = furnitureDTOConverter.convertToDTO(furnitureList.getSelectedValue().toString());
                    furnitureService.deleteFurniture(oldFurnitureDTO);
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
                DealDTO dealDTO = new DealDTO();
            }
        }
    }
}
