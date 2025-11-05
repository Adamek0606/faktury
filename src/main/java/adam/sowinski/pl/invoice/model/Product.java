package adam.sowinski.pl.invoice.model;

import java.math.BigDecimal;

/**
 * GRASP: Information Expert
 * Reprezentuje artykuł/produkt z ceną jednostkową.
 */
public class Product {
    private String id;
    private String name;
    private BigDecimal unitPrice;

    public Product(String id, String name, BigDecimal unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", unitPrice=" + unitPrice +
               '}';
    }
}
