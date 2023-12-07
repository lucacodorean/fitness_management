package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Client extends Model {

  private Integer id;
  private String email;
  private String lastName;
  private String firstName;
  private Boolean hasActiveSub;
  private Subscription subscription;
  private LocalDateTime nextPaymentAt;
  public static String tableName = "clients";

  public void setId(Integer id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setIsSubscribed(Boolean hasActiveSub) {
    this.hasActiveSub = hasActiveSub;
  }

  public void setSubscription(Subscription subscription) {
    this.subscription = subscription;
  }

  public void setNextPaymentAt(LocalDateTime nextPaymentAt) {
    this.nextPaymentAt = nextPaymentAt;
  }

  public Integer getId() {
    return this.id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public Boolean isSubscribed() {
    return this.hasActiveSub;
  }

  public Subscription getSubscription() {
    return this.subscription;
  }

  public LocalDateTime getNextPaymentAt() {
    return this.nextPaymentAt;
  }

  public Client() {
    this.settableName("clients");
  }

  public Client(
    Integer id,
    String firstName,
    String lastName,
    String email,
    Boolean hasActiveSub,
    Subscription subscription,
    LocalDateTime nextPaymentAt
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
    Client checkedClient = new Client();

    ResultSet temp = checkedClient.find(id);

    if (temp != null) {
      checkedClient.setId(temp.getInt(1));
      checkedClient.setFirstName(temp.getString(2));
      checkedClient.setLastName(temp.getString(3));
      checkedClient.setEmail(temp.getString(4));
      checkedClient.setIsSubscribed(temp.getBoolean(5));
      checkedClient.setNextPaymentAt(temp.getTimestamp(7).toLocalDateTime());

      Subscription tempSubscription = new Subscription();

      ResultSet temp2 = tempSubscription.find(temp.getInt(6));
      if (temp2 != null) {
        tempSubscription =
          new Subscription(
            temp2.getInt(1),
            temp2.getInt(2),
            temp2.getString(3)
          );
      }

      checkedClient.setSubscription(tempSubscription);
    }

    return checkedClient;
  }

  @Override
  public String toString() {
    return (
      "Client [id=" +
      this.getId() +
      ", email=" +
      this.getEmail() +
      ", lastName=" +
      this.getLastName() +
      ", firstName=" +
      this.getFirstName() +
      ", hasActiveSub=" +
      this.isSubscribed() +
      ", subscription=" +
      this.getSubscription().toString() +
      ", nextPaymentAt=" +
      this.getNextPaymentAt() +
      "]"
    );
  }

  public static String[] getColumns() {
    return new String[] {
      "ID",
      "Nume",
      "Prenume",
      "Email",
      "Are abonament platit",
      "Tip abonament",
      "Urmatoarea scadenta",
    };
  }

  public Client[] getTableDataFormat() {
    try {
      ResultSet set = this.all();
      Client[] data = new Client[Client.count(this.all())];

      if (data.length == 0) return new Client[0];
      int i = 0;

      while (set.next()) {
        data[i++] = Client.getDetails(set.getInt(1));
      }

      return data;
    } catch (SQLException e) {
      e.printStackTrace();
      return new Client[0];
    }
  }
}
