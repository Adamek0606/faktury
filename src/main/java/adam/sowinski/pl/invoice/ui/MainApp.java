package adam.sowinski.pl.invoice.ui;

import adam.sowinski.pl.invoice.model.Client;
import adam.sowinski.pl.invoice.model.Invoice;
import adam.sowinski.pl.invoice.model.Product;
import adam.sowinski.pl.invoice.service.ClientService;
import adam.sowinski.pl.invoice.service.InvoiceService;
import adam.sowinski.pl.invoice.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * GRASP: Controller
 * Prosty interfejs konsolowy do dodawania klientów, produktów i tworzenia faktur.
 * Uruchom: mvn exec:java (albo uruchom klasę z IDE).
 */
public class MainApp {
    private static ClientService clientService = new ClientService();
    private static ProductService productService = new ProductService();
    private static InvoiceService invoiceService = new InvoiceService();

    public static void main(String[] args) {
        seedSampleData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String option = scanner.nextLine().trim();
            switch (option) {
                case "1":
                    addClient(scanner);
                    break;
                case "2":
                    addProduct(scanner);
                    break;
                case "3":
                    createInvoice(scanner);
                    break;
                case "4":
                    listInvoices();
                    break;
                case "5":
                    listClients();
                    break;
                case "6":
                    listProducts();
                    break;
                case "0":
                    System.out.println("Koniec. Do widzenia!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Nieznana opcja. Spróbuj ponownie.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("=== Invoice System ===");
        System.out.println("1) Dodaj klienta");
        System.out.println("2) Dodaj produkt");
        System.out.println("3) Utwórz fakturę");
        System.out.println("4) Wyświetl wszystkie faktury");
        System.out.println("5) Wyświetl klientów");
        System.out.println("6) Wyświetl produkty");
        System.out.println("0) Wyjście");
        System.out.print("Wybierz opcję: ");
    }

    private static void addClient(Scanner scanner) {
        System.out.print("ID klienta: ");
        String id = scanner.nextLine().trim();
        System.out.print("Nazwa: ");
        String name = scanner.nextLine().trim();
        System.out.print("NIP: ");
        String nip = scanner.nextLine().trim();
        System.out.print("Adres: ");
        String address = scanner.nextLine().trim();
        Client c = clientService.addClient(id, name, nip, address);
        System.out.println("Dodano klienta: " + c);
    }

    private static void addProduct(Scanner scanner) {
        System.out.print("ID produktu: ");
        String id = scanner.nextLine().trim();
        System.out.print("Nazwa produktu: ");
        String name = scanner.nextLine().trim();
        System.out.print("Cena jednostkowa (np. 19.99): ");
        String priceStr = scanner.nextLine().trim();
        BigDecimal price;
        try {
            price = new BigDecimal(priceStr);
        } catch (NumberFormatException ex) {
            System.out.println("Niepoprawna cena. Operacja anulowana.");
            return;
        }
        Product p = productService.addProduct(id, name, price);
        System.out.println("Dodano produkt: " + p);
    }

    private static void createInvoice(Scanner scanner) {
        System.out.println("Wybierz ID klienta:");
        listClients();
        System.out.print("ID klienta: ");
        String clientId = scanner.nextLine().trim();
        Client client = clientService.findById(clientId);
        if (client == null) {
            System.out.println("Klient nie istnieje. Najpierw dodaj klienta.");
            return;
        }
        Invoice invoice = invoiceService.createInvoice(client);
        System.out.println("Tworzenie faktury o ID: " + invoice.getId());
        while (true) {
            System.out.println("Dodaj pozycję do faktury:");
            listProducts();
            System.out.print("ID produktu (lub 'done' aby zakończyć): ");
            String prodId = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(prodId)) break;
            Product product = productService.findById(prodId);
            if (product == null) {
                System.out.println("Produkt nie znaleziony.");
                continue;
            }
            System.out.print("Ilość: ");
            String qtyStr = scanner.nextLine().trim();
            int qty;
            try {
                qty = Integer.parseInt(qtyStr);
            } catch (NumberFormatException ex) {
                System.out.println("Niepoprawna ilość.");
                continue;
            }
            try {
                invoiceService.addItemToInvoice(invoice, product, qty);
                System.out.println("Dodano pozycję.");
            } catch (IllegalArgumentException ex) {
                System.out.println("Błąd: " + ex.getMessage());
            }
        }
        System.out.println("Faktura utworzona:\n" + invoice);
    }

    private static void listInvoices() {
        List<Invoice> invoices = invoiceService.listAll();
        if (invoices.isEmpty()) {
            System.out.println("Brak faktur.");
            return;
        }
        for (Invoice inv : invoices) {
            System.out.println(inv);
        }
    }

    private static void listClients() {
        List<Client> clients = clientService.listAll();
        if (clients.isEmpty()) {
            System.out.println("Brak klientów.");
            return;
        }
        for (Client c : clients) {
            System.out.println(c.getId() + " : " + c.getName());
        }
    }

    private static void listProducts() {
        List<Product> products = productService.listAll();
        if (products.isEmpty()) {
            System.out.println("Brak produktów.");
            return;
        }
        for (Product p : products) {
            System.out.println(p.getId() + " : " + p.getName() + " - " + p.getUnitPrice().toPlainString());
        }
    }

    private static void seedSampleData() {
        // pomocnicze dane startowe
        clientService.addClient("C1", "Firma A", "123-456-78-90", "ul. Przykładowa 1");
        clientService.addClient("C2", "Jan Kowalski", "987-654-32-10", "ul. Inna 5");

        productService.addProduct("P1", "Mysz USB", new BigDecimal("59.99"));
        productService.addProduct("P2", "Klawiatura", new BigDecimal("129.00"));
        productService.addProduct("P3", "Monitor 24\"", new BigDecimal("599.00"));
    }
}
