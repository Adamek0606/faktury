package adam.sowinski.pl.invoice.model;

import java.math.BigDecimal;

/**
 * GRASP: Information Expert
 * Reprezentuje pojedynczą pozycję faktury (produkt + ilość + cena jednostkowa).
 */
public class InvoiceItem {
    private Product product;
    private int quantity;
    private BigDecimal unitPrice; // przechowujemy wartość jednostkową w momencie dodania

    public InvoiceItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }
        this.product = product;
        this.quantity = quantity;
        // kopiujemy cenę jednostkową z produktu (snapshot)
        this.unitPrice = product.getUnitPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return String.format("%s x %d @ %s = %s",
                product.getName(),
                quantity,
                unitPrice.toPlainString(),
                getTotal().toPlainString());
    }
}
