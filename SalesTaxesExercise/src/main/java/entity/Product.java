package entity;

import utils.Category;

public class Product {

    protected String name;
    protected Category category;
    protected float price;
    protected boolean imported;

    // Quantity added
    protected int quantity;

    public Product() {
    }

    public Product(String name, Category category, float price, boolean imported, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imported = imported;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (imported != product.imported) return false;
        if (Float.compare(product.price, price) != 0) return false;
        if (quantity != product.quantity) return false;
        if (category != product.category) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (imported ? 1 : 0);
        result = 31 * result + quantity;
        return result;
    }
}