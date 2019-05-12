package com.bestfurnituredeals.assignment3.service.query.impl;

import com.bestfurnituredeals.assignment3.dao.ProductDAO;
import com.bestfurnituredeals.assignment3.exception.NoProductFoundException;
import com.bestfurnituredeals.assignment3.model.database.Product;
import com.bestfurnituredeals.assignment3.service.query.ClientOrderQueryService;
import com.bestfurnituredeals.assignment3.service.query.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductQueryServiceImpl implements ProductQueryService {
    private ClientOrderQueryService clientOrderQueryServiceService;
    private ProductDAO productDAO;

    @Autowired
    public ProductQueryServiceImpl(ClientOrderQueryService clientOrderQueryServiceService, ProductDAO productDAO) {
        this.clientOrderQueryServiceService = clientOrderQueryServiceService;
        this.productDAO = productDAO;
    }

    @Override
    public Product getProductById(long id) {
        return productDAO.findById(id).orElseThrow(() -> new NoProductFoundException("No product with id " + id + " found!"));
    }

    @Override
    public List<Product> getProductsForAClientOrder(long id) {
        return productDAO.findProductsByClientOrderId(id);
    }

    @Override
    public List<Product> getProductsForAClientsCurrentOrder(String username) {
        return productDAO.findProductsByClientOrderId(clientOrderQueryServiceService.getCurrentClientOrderForAnUser(username).getId());
    }
}
