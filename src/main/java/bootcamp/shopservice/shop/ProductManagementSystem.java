package bootcamp.shopservice.shop;

import bootcamp.shopservice.order.Order;
import bootcamp.shopservice.order.OrderMapRepo;
import bootcamp.shopservice.order.OrderRepo;
import bootcamp.shopservice.product.Product;
import bootcamp.shopservice.product.ProductRepo;

import java.math.BigDecimal;
import java.util.*;

public class ProductManagementSystem {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final ShopService shopService;
    private final Scanner scanner;

    public ProductManagementSystem() {
        this.productRepo = new ProductRepo();
        this.orderRepo = new OrderMapRepo(); // oder OrderListRepo
        this.shopService = new ShopService(orderRepo, productRepo);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println(ANSI_BLUE + "Produktverwaltungssystem" + ANSI_RESET);
            System.out.println("1. Produkt hinzufügen");
            System.out.println("2. Produkt anzeigen");
            System.out.println("3. Bestellung aufgeben");
            System.out.println("4. Programm beenden");
            System.out.print("Wählen Sie eine Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Zeilenumbruch konsumieren

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    System.out.println(ANSI_RED + "Programm beendet." + ANSI_RESET);
                    return;
                default:
                    System.out.println(ANSI_YELLOW + "Ungültige Auswahl. Bitte versuchen Sie es erneut." + ANSI_RESET);
            }
        }
    }

    private void addProduct() {
        System.out.print("Produkt-ID: ");
        String id = scanner.nextLine();
        System.out.print("Produktname: ");
        String name = scanner.nextLine();
        System.out.print("Preis: ");
        BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());
        System.out.print("Menge: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Zeilenumbruch konsumieren

        Product product = new Product(id, name, price, quantity);
        productRepo.add(product);
        System.out.println(ANSI_GREEN + "Produkt hinzugefügt: " + product + ANSI_RESET);
    }

    private void displayProducts() {
        List<Product> products = productRepo.getProducts();
        if (products.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Keine Produkte vorhanden." + ANSI_RESET);
        } else {
            System.out.println(ANSI_CYAN + "Produkte:" + ANSI_RESET);
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private void placeOrder() {
        System.out.print("Bestell-ID: ");
        String orderId = scanner.nextLine();
        System.out.print("Anzahl der Produkte: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Zeilenumbruch konsumieren

        //List<Product> orderedProducts = new ArrayList<>();
        Map<Product, Integer> mapProductQuantity = new HashMap<>();
        //List<Integer> quantities = new ArrayList<>();
        //Order order1 = new Order("1", mapProductQuantity);

        for (int i = 0; i < count; i++) {
            System.out.print("Produkt-ID für Produkt " + (i + 1) + ": ");
            String productId = scanner.nextLine();
            System.out.print("Menge für Produkt " + (i + 1) + ": ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Zeilenumbruch konsumieren

            Product product = productRepo.getProduct(productId);
            //if (product != null && product.stockQuantity() >= quantity) {
            if (productRepo.isProductInStock(productId, quantity)) {
                mapProductQuantity.put(product, quantity);
                //orderedProducts.add(product);
                //quantities.add(quantity);
            } else {
                System.out.println(ANSI_RED + "Produkt mit ID " + productId + " ist nicht verfügbar oder nicht ausreichend auf Lager." + ANSI_RESET);
                return;
            }
        }
        Order order = new Order(orderId, mapProductQuantity);
        shopService.placeOrder(order);
        System.out.println(ANSI_GREEN + "Bestellung aufgegeben: " + order + ANSI_RESET);
    }
}