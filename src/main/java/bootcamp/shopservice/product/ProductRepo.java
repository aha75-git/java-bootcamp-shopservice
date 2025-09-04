package bootcamp.shopservice.product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private final List<Product> products;

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

    public void removeAll() {
        this.products.clear();
    }

    public boolean isEmpty() {
        return this.products.isEmpty();
    }

    public boolean updateStock(String productId, int quantityChange) {
        Product product = this.getProduct(productId);
        if (product != null) {
            if (quantityChange < 0) {
                return false;
            }

            products.remove(product);
            products.add(new Product(product.id(), product.name(), product.price(), quantityChange));
            return true;
        }
        return false;
    }

    public boolean isProductInStock(String productId, int quantity) {
        Product product = this.getProduct(productId);
        return product != null && product.stockQuantity() >= quantity;
    }
}
