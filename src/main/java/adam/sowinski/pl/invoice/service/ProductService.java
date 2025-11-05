package adam.sowinski.pl.invoice.service;

import adam.sowinski.pl.invoice.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * GRASP: Pure Fabrication / Controller
 * Zarządza kolekcją produktów (dodawanie, wyszukiwanie).
 */
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public Product addProduct(String id, String name, BigDecimal unitPrice) {
        Product p = new Product(id, name, unitPrice);
        products.add(p);
        return p;
    }

    public Product findById(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Product> listAll() {
        return new ArrayList<>(products);
    }
}
