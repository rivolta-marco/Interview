package processor;

import entity.Product;
import entity.ShoppingBasket;
import utils.Category;

import java.math.BigDecimal;
import java.util.List;

public class SalesTaxesProcessor {

    // Default values in case they're not specified
    public SalesTaxesProcessor() {
        this.importTaxMultiplier = 0.05f;
        this.sellTaxMultiplier = 0.1f;
    }

    // Variables, just to make the program consistent "if" in the future the taxes may change
    float importTaxMultiplier;
    float sellTaxMultiplier;

    public SalesTaxesProcessor(float importTaxMultiplier, float sellTaxMultiplier) {
        this.importTaxMultiplier = importTaxMultiplier;
        this.sellTaxMultiplier = sellTaxMultiplier;
    }

    // Getters and setters not strictly needed for this exercise, however always a good practice if need be
    public float getImportTaxMultiplier() {
        return importTaxMultiplier;
    }

    public void setImportTaxMultiplier(float importTaxMultiplier) {
        this.importTaxMultiplier = importTaxMultiplier;
    }

    public float getSellTaxMultiplier() {
        return sellTaxMultiplier;
    }

    public void setSellTaxMultiplier(float sellTaxMultiplier) {
        this.sellTaxMultiplier = sellTaxMultiplier;
    }

    // Main method, gets in input the List of products, and returns the receipt as String, to be processed in any desired way afterwards
    public String createReceipt(ShoppingBasket basket) {

        float salesTaxes = 0f, total = 0f;
        StringBuilder sb = new StringBuilder();

        for(Product product : basket.getProducts()) {
            int quantity = product.getQuantity();
            sb.append(quantity).append(" ");

            sb.append(product.getName()).append(" : ");

            float tax = 0f, price = product.getPrice();

            tax += product.isImported() ? price * importTaxMultiplier : tax;
            tax += product.getCategory() == Category.TAX_FREE ? 0 : price * sellTaxMultiplier;

            price += tax;

            // Quantity introduced to make the exercise a bit more realistic
            tax = round(tax * quantity);
            price = round(price * quantity);

            total += price;
            salesTaxes += tax;

            sb.append(String.format("%.2f",price)).append("\n");
        }

        sb.append("Sales Taxes : ").append(String.format("%.2f", salesTaxes)).append("\n");
        sb.append("Total : ").append(String.format("%.2f",total));

        return sb.toString();

    }

    // Round method created separately, in case in the future the rounding strategy will change
    public float round(float num) {
        BigDecimal rounded = BigDecimal.valueOf(num);

        return  rounded.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesTaxesProcessor processor = (SalesTaxesProcessor) o;

        return Float.compare(processor.importTaxMultiplier, importTaxMultiplier) == 0 && Float.compare(processor.sellTaxMultiplier, sellTaxMultiplier) == 0;

    }

    @Override
    public int hashCode() {
        int result = (importTaxMultiplier != +0.0f ? Float.floatToIntBits(importTaxMultiplier) : 0);
        result = 31 * result + (sellTaxMultiplier != +0.0f ? Float.floatToIntBits(sellTaxMultiplier) : 0);
        return result;
    }
}