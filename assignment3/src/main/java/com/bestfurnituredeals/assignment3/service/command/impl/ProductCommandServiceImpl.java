package com.bestfurnituredeals.assignment3.service.command.impl;

import com.bestfurnituredeals.assignment3.dao.ProductDAO;
import com.bestfurnituredeals.assignment3.decorator.IProduct;
import com.bestfurnituredeals.assignment3.decorator.ReducedProduct;
import com.bestfurnituredeals.assignment3.exception.DealUnavailableException;
import com.bestfurnituredeals.assignment3.exception.NotEnoughProductsOnStockException;
import com.bestfurnituredeals.assignment3.exception.OrderAlreadyPlacedException;
import com.bestfurnituredeals.assignment3.model.database.ClientOrder;
import com.bestfurnituredeals.assignment3.model.database.Deal;
import com.bestfurnituredeals.assignment3.model.database.DealType;
import com.bestfurnituredeals.assignment3.model.database.Product;
import com.bestfurnituredeals.assignment3.service.command.DealCommandService;
import com.bestfurnituredeals.assignment3.service.command.ProductCommandService;
import com.bestfurnituredeals.assignment3.service.query.ClientOrderQueryService;
import com.bestfurnituredeals.assignment3.service.query.DealQueryService;
import com.bestfurnituredeals.assignment3.service.query.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductCommandServiceImpl implements ProductCommandService {
    private DealQueryService dealQueryService;
    private DealCommandService dealCommandService;
    private ProductQueryService productQueryService;
    private ClientOrderQueryService clientOrderQueryService;
    private ProductDAO productDAO;

    @Autowired
    public ProductCommandServiceImpl(DealQueryService dealQueryService, DealCommandService dealCommandService, ProductQueryService productQueryService, ClientOrderQueryService clientOrderQueryService, ProductDAO productDAO) {
        this.dealQueryService = dealQueryService;
        this.dealCommandService = dealCommandService;
        this.productQueryService = productQueryService;
        this.clientOrderQueryService = clientOrderQueryService;
        this.productDAO = productDAO;
    }

    @Override
    public void addProductToOrder(long dealId, String username, int quantity) {
        Deal deal = dealQueryService.getDealById(dealId);
        if (deal.getAvailableQuantity() < quantity) {
            throw new NotEnoughProductsOnStockException();
        }
        if (!deal.isAvailable()) {
            throw new DealUnavailableException();
        }
        ClientOrder clientOrder = clientOrderQueryService.getCurrentClientOrderForAnUser(username);
        IProduct product = null;
        if (deal.getDealType() == DealType.REDUCED) {
            product = new ReducedProduct(new Product(quantity, deal, clientOrder));
        } else if (deal.getDealType() == DealType.NORMAL) {
            product = new Product(quantity, deal, clientOrder);
        }
        product.decorate();
        productDAO.save((Product) product.getProduct());
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productQueryService.getProductById(id);
        if (product.getClientOrder().isFinished()) {
            throw new OrderAlreadyPlacedException();
        }
        Deal deal = product.getDeal();
        deal.setAvailableQuantity(deal.getAvailableQuantity() + product.getSelectedQuantity());
        productDAO.delete(product);
        dealCommandService.updateDeal(deal);
    }
}
