package adam.sowinski.pl.invoice.service;

import adam.sowinski.pl.invoice.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * GRASP: Pure Fabrication / Controller
 * Serwis odpowiedzialny za zarządzanie klientami (dodawanie, wyszukiwanie).
 * Umożliwia oddzielenie logiki przechowywania klientów od modelu Client.
 */
public class ClientService {
    private List<Client> clients = new ArrayList<>();

    public Client addClient(String id, String name, String nip, String address) {
        Client c = new Client(id, name, nip, address);
        clients.add(c);
        return c;
    }

    public Client findById(String id) {
        for (Client c : clients) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public List<Client> listAll() {
        return new ArrayList<>(clients);
    }
}
