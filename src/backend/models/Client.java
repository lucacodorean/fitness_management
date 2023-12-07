package backend.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Client extends Model {
    
    private Integer         id;
    private String          email;
    private String          lastName;
    private String          firstName;
    private Boolean         hasActiveSub;
    private Subscription    subscription;
    private LocalDateTime   nextPaymentAt;

    
    public void setId(Integer id)                               { this.id = id; }
    public void setEmail(String email)                          { this.email = email; }
    public void setFirstName(String firstName)                  { this.firstName = firstName; }
    public void setLastName(String lastName)                    { this.lastName = lastName; }
    public void setIsSubscribed(Boolean hasActiveSub)           { this.hasActiveSub = hasActiveSub; }
    public void setSubscription(Subscription subscription)      { this.subscription = subscription; }
    public void setNextPaymentAt(LocalDateTime nextPaymentAt)   { this.nextPaymentAt = nextPaymentAt; }
    
    public Integer getId()                                      { return this.id; }
    public String getEmail()                                    { return this.email; }
    public String getLastName()                                 { return this.lastName; }
    public String getFirstName()                                { return this.firstName; }
    public Boolean isSubscribed()                               { return this.hasActiveSub; }
    public Subscription getSubscription()                       { return this.subscription; }
    public LocalDateTime getNextPaymentAt()                     { return this.nextPaymentAt; }

    public Client() {
        this.settableName("clients");
    }

    public Client(
        Integer id, String firstName, String lastName, String email,
        Boolean hasActiveSub, Subscription subscription, LocalDateTime nextPaymentAt
    ) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setIsSubscribed(hasActiveSub);
        this.setSubscription(subscription);
        this.setNextPaymentAt(nextPaymentAt);

        this.settableName("clients");
    }

    public static Client getDetails(Integer id) throws SQLException {

        ResultSet temp = new Client().find(id);
        
        return temp != null ? new Client(
            temp.getInt(1), temp.getString(2), temp.getString(3), temp.getString(4),
            temp.getBoolean(5), Subscription.getDetails(temp.getInt(6)), 
            LocalDateTime.parse(
                temp.getString(7), 
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            )) : null ;
    }

    @Override
    public String toString() {
        return 
            "Client [id=" + this.getId() + ", email=" + this.getEmail() + ", lastName=" + this.getLastName() + 
                ", firstName=" + this.getFirstName() + ", hasActiveSub=" + this.isSubscribed() + 
                ", subscription=" + this.getSubscription().toString() + ", nextPaymentAt=" + this.getNextPaymentAt() + 
            "]";
    }
}
