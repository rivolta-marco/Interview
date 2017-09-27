import entity.Product;
import entity.ShoppingBasket;
import org.junit.Test;
import processor.SalesTaxesProcessor;
import utils.Category;
import utils.CsvReader;

public class SalesTaxProcessorTest {

    @Test
    public void testSalesTaxProcessor() {
        // Tax rates can be modified
        SalesTaxesProcessor processor = new SalesTaxesProcessor(0.05f, 0.1f);

        String inputFile = "C:\\Apps\\interview.csv";

//        Product product1 = new Product("book", Category.TAX_FREE, 12.49f, false);
//        Product product2 = new Product("music CD", Category.NORMAL, 14.99f, false);
//        Product product3 = new Product("chocolate bar", Category.TAX_FREE, 0.85f, false);

//        Product product1 = new Product("box of chocolate", Category.TAX_FREE, 10.00f, true);
//        Product product2 = new Product("bottle of perfume", Category.NORMAL, 47.50f, true);

        Product product1 = new Product("bottle of perfume", Category.NORMAL, 27.99f, true, 1);
        Product product2 = new Product("bottle of perfume", Category.NORMAL, 18.99f, false, 1);
        Product product3 = new Product("packet of headache pills", Category.TAX_FREE, 9.75f, false, 1);
        Product product4 = new Product("box of chocolates", Category.TAX_FREE, 11.25f, true, 1);

        CsvReader<Product> reader = new CsvReader<Product>();
        try {

            ShoppingBasket basket = new ShoppingBasket(reader.process(inputFile, Product.class));
            String receipt = processor.createReceipt(basket);

            // Simple System out have been implemented, the result can be processed in many ways
            System.out.println(receipt);

        } catch (Exception e) {
            System.out.println("Data extraction failed!!! Please check that the Input file format is complient with the requirements");
        }
    }

}
