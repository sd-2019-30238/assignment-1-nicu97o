package com.bestFurnitureDeals.service.impl;

import com.bestFurnitureDeals.dao.ProductDAO;
import com.bestFurnitureDeals.exception.DealUnavailableException;
import com.bestFurnitureDeals.exception.NoProductFoundException;
import com.bestFurnitureDeals.exception.NotEnoughProductsOnStockException;
import com.bestFurnitureDeals.exception.OrderAlreadyPlacedException;
import com.bestFurnitureDeals.factory.ProductCreatorFactory;
import com.bestFurnitureDeals.model.ClientOrder;
import com.bestFurnitureDeals.model.Deal;
import com.bestFurnitureDeals.model.Product;
import com.bestFurnitureDeals.service.ClientOrderService;
import com.bestFurnitureDeals.service.DealService;
import com.bestFurnitureDeals.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private DealService dealService;
    private ClientOrderService clientOrderService;
    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(DealService dealService, ClientOrderService clientOrderService, ProductDAO productDAO) {
        this.dealService = dealService;
        this.clientOrderService = clientOrderService;
        this.productDAO = productDAO;
    }

    @Override
    public Product getProductById(long id) {
        return productDAO.findById(id).orElseThrow(() -> new NoProductFoundException("No product with id " + id + " found!"));
    }

    @Override
    public void addProductToOrder(long dealId, String username, int quantity) {
        Deal deal = dealService.getDealById(dealId);
        if (deal.getAvailableQuantity() < quantity) {
            throw new NotEnoughProductsOnStockException();
        }
        if(!deal.isAvailable()){
            throw new DealUnavailableException();
        }
        ClientOrder clientOrder = clientOrderService.getCurrentClientOrderForAnUser(username);
        Product product = ProductCreatorFactory.getInstance(deal.getDealType()).createProduct(clientOrder, deal, quantity);
        productDAO.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = getProductById(id);
        if (product.getClientOrder().isFinished()) {
            throw new OrderAlreadyPlacedException();
        }
        Deal deal = product.getDeal();
        deal.setAvailableQuantity(deal.getAvailableQuantity() + product.getSelectedQuantity());
        productDAO.delete(product);
        dealService.updateDeal(deal);
    }

    @Override
    public List<Product> getProductsForAClientOrder(long id) {
        return productDAO.findProductsByClientOrderId(id);
    }

    @Override
    public List<Product> getProductsForAClientsCurrentOrder(String username) {
        return productDAO.findProductsByClientOrderId(clientOrderService.getCurrentClientOrderForAnUser(username).getId());
    }
}
