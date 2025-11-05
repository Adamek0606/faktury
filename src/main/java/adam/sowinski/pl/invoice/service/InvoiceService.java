package adam.sowinski.pl.invoice.service;

import adam.sowinski.pl.invoice.model.Client;
import adam.sowinski.pl.invoice.model.Invoice;
import adam.sowinski.pl.invoice.model.InvoiceItem;
import adam.sowinski.pl.invoice.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GRASP: Controller, Creator
 * Serwis odpowiedzialny za tworzenie i przechowywanie faktur.
 * Stosuje prosty licznik ID (AtomicInteger) do nadawania identyfikatorów.
 */
public class InvoiceService {
    private List<Invoice> invoices = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public Invoice createInvoice(Client client) {
        String id = String.valueOf(idCounter.getAndIncrement());
        Invoice invoice = new Invoice(id, client);
        invoices.add(invoice);
        return invoice;
    }

    public void addItemToInvoice(Invoice invoice, Product product, int quantity) {
        if (invoice == null || product == null) {
            throw new IllegalArgumentException("Invoice and product must not be null");
        }
        InvoiceItem item = new InvoiceItem(product, quantity);
        invoice.addItem(item);
    }

    public Invoice findById(String id) {
        for (Invoice inv : invoices) {
            if (inv.getId().equals(id)) {
                return inv;
            }
        }
        return null;
    }

    public List<Invoice> listAll() {
        return new ArrayList<>(invoices);
    }
}
