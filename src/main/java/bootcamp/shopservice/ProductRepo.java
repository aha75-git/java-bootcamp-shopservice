package bootcamp.shopservice;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private List<Product> products;

    public ProductRepo() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public Product getProduct(String productId) {
        for (Product product : products) {
            if (product.id().equals(productId)) {
                return product;
            }
        }
        return null;
        /* return products.stream()
                .filter(product -> product.id().equals(productId))
                .findFirst()
                .orElse(null); */
    }

    public boolean add(Product product) {
        return this.products.add(product);
    }

    public boolean remove(Product product) {
        return this.products.remove(product);
    }

    public boolean remove(String productId) {
        return this.products.removeIf(product -> product.id().equals(productId));
    }
}
