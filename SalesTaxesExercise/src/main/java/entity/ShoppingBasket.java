package entity;

import java.util.List;

// This Class have been create only for scalability purposes, it could be modified in the future for many purposes
public class ShoppingBasket {

    private List<Product> products;

    public ShoppingBasket() {
    }

    public ShoppingBasket(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
