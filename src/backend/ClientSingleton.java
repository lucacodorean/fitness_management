package backend;

import backend.models.Client;

public final class ClientSingleton {

    private static final ClientSingleton CURRENT_INSTANCE = new ClientSingleton();
    private Client client;

    private ClientSingleton() { }

    public static ClientSingleton getCurrentInstance() { return CURRENT_INSTANCE; }
    public void   setClient(Client clientParam) { this.client = clientParam; }
    public Client getClient()                   { return this.client; }
}
