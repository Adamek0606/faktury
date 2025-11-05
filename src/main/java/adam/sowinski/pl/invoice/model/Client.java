package adam.sowinski.pl.invoice.model;

/**
 * GRASP: Information Expert
 * Klasa Client przechowuje dane klienta — to ona posiada informacje potrzebne dla faktur.
 */
public class Client {
    private String id;
    private String name;
    private String nip;
    private String address;

    public Client(String id, String name, String nip, String address) {
        this.id = id;
        this.name = name;
        this.nip = nip;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNip() {
        return nip;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Client{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", nip='" + nip + '\'' +
               ", address='" + address + '\'' +
               '}';
    }
}
