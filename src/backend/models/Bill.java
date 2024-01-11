package backend.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bill extends Model{
    private Integer                    id;
    private Client                 client;
    private Subscription     subscription;
    private LocalDateTime        payed_at;

    public Integer getId()                  { return id; }
    public Client getClient()               { return client; }
    public LocalDateTime getPayed_at()      { return payed_at; }
    public Subscription getSubscription()   { return subscription; }

    public void setId(Integer id) { this.id = id; }
    public void setClient(Client client) { this.client = client; }
    public void setSubscription(Subscription subscription) { this.subscription = subscription; }
    public void setPayed_at(LocalDateTime payed_at) { this.payed_at = payed_at; }


    public Bill() {
        settableName("payments");
    }

    public Bill(Integer id, Client client, Subscription subscription, LocalDateTime time) {
        setId(id);
        setClient(client);
        setSubscription(subscription);
        setPayed_at(time);
    }

    public static Bill getDetails(Integer id) throws SQLException {
        ResultSet temp = new Bill().find(id);

        return temp != null ? new Bill(
            temp.getInt(1), Client.getDetails(temp.getInt(2)), Subscription.getDetails(temp.getInt(3)),
            LocalDateTime.parse(
                temp.getString(4),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        ) : null;
    }
}
