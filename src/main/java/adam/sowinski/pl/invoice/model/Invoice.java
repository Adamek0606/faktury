package adam.sowinski.pl.invoice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GRASP: Information Expert, Creator
 * Invoice przechowuje listę InvoiceItem, oblicza sumę faktury.
 * Invoice tworzy i posiada swoje pozycje.
 */
public class Invoice {
    private String id;
    private Client client;
    private List<InvoiceItem> items = new ArrayList<>();

    public Invoice(String id, Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        this.id = id;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void addItem(InvoiceItem item) {
        if (item == null) {
            throw new IllegalArgumentException("InvoiceItem cannot be null");
        }
        items.add(item);
    }

    public List<InvoiceItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (InvoiceItem item : items) {
            sum = sum.add(item.getTotal());
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice ID: ").append(id).append("\n");
        sb.append("Client: ").append(client.getName()).append(" (NIP: ").append(client.getNip()).append(")\n");
        sb.append("Address: ").append(client.getAddress()).append("\n");
        sb.append("Items:\n");
        for (InvoiceItem it : items) {
            sb.append("  - ").append(it.toString()).append("\n");
        }
        sb.append("TOTAL: ").append(getTotal().toPlainString()).append("\n");
        return sb.toString();
    }
}
