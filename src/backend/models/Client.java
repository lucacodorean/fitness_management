package backend.models;

import frontend.Window;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.sql.SQLException;
import java.time.LocalDateTime;

import backend.ClientSingleton;
import backend.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import java.time.format.DateTimeFormatter;

public class Client extends Model {
    
    private Integer         id;
    private String          email;
    private String          lastName;
    private String          firstName;
    private Boolean         hasActiveSub;
    private Subscription    subscription;
    private LocalDateTime   nextPaymentAt;

    private Button additionalInformation;
    private Button renewSubscription;
    private Button logEntryIn;
    private Button logEntryOut;

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
    
    public Button getLogEntryIn()                               { return this.logEntryIn; }
    public Button getLogEntryOut()                              { return this.logEntryOut; }
    public Button getRenewSubscription()                        { return this.renewSubscription; }
    public Button getAdditionalInformation()                    { return this.additionalInformation; }

    public String getHasActiveSub() { return Boolean.TRUE.equals(hasActiveSub) ? "Da" : "Nu"; }

    public Client() {
        this.settableName("clients");
        additionalInformation = new Button("Additional information");
        renewSubscription = new Button("New Subscription");
        logEntryOut       = new Button("Log Entry Out");
        logEntryIn        = new Button("Log Entry In");
        
        
        renewSubscription.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent e) {
                try{
                    ClientSingleton.getCurrentInstance().setClient(Client.getDetails(getId()));
                    Window.getInstance().setView("renew_subscription"); 
                } catch(Exception ex) { 
                    System.err.println(ex.toString());
                }
            }
        });

        logEntryIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) { 
                DatabaseManager database = new DatabaseManager();
                
                ArrayList<String> parameters = new ArrayList<String>();
                parameters.add(getId().toString());
                parameters.add("5");
                parameters.add(java.time.LocalDateTime.now().toString());
    
                try{ 
                    database.updatePreparedSQL("insert into jurnal (client_id, event_id, created_at) values (?,?,?)", parameters);
                    JFrame frame = new JFrame("Notification Box");
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            JOptionPane.showMessageDialog(
                                frame,
                                "Inregistrarea in jurnalul de evenimente a intrarii s-a efectuat cu succes.",
                                "SUCCES",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    });
                } catch(Exception ex) {
                    System.err.println(ex.toString());
                    
                    JFrame frame = new JFrame("Notification Box");
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            JOptionPane.showMessageDialog(
                                frame,
                                "Eroare la scrierea in jurnalul de evenimente.", 
                                "ERROR", 
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    });
                }
            }
        });

        logEntryOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) { 
                DatabaseManager database = new DatabaseManager();
                
                ArrayList<String> parameters = new ArrayList<String>();
                parameters.add(getId().toString());
                parameters.add("6");
                parameters.add(java.time.LocalDateTime.now().toString());
    
                try{ 
                    database.updatePreparedSQL("insert into jurnal (client_id, event_id, created_at) values (?,?,?)", parameters);
                    JFrame frame = new JFrame("Notification Box");
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            JOptionPane.showMessageDialog(
                                frame,
                                "Inregistrarea in jurnalul de evenimente a intrarii s-a efectuat cu succes.",
                                "SUCCES",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    });
                } catch(Exception ex) {
                    System.err.println(ex.toString());

                    JFrame frame = new JFrame("Notification Box");
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            JOptionPane.showMessageDialog(
                                frame,
                                "Eroare la scrierea in jurnalul de evenimente.", 
                                "ERROR", 
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    });
                }
            }
        });

        additionalInformation.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try{
                    ClientSingleton.getCurrentInstance().setClient(Client.getDetails(getId()));
                    Window.getInstance().setView("clients_information"); 
                } catch(Exception ex) { 
                    System.err.println(ex.toString());
                }
            }
        });
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
        Client checkedClient = new Client();

        ResultSet temp = checkedClient.find(id);
        
        if(temp != null) {
            checkedClient.setId(temp.getInt(1));
            checkedClient.setFirstName(temp.getString(2));
            checkedClient.setLastName(temp.getString(3));
            checkedClient.setEmail(temp.getString(4));
            checkedClient.setIsSubscribed(temp.getBoolean(5));
            
            Subscription tempSubscription = new Subscription();
            
            ResultSet temp2 = tempSubscription.find(temp.getInt(6));
            if(temp2 != null) {
                tempSubscription = new Subscription(
                    temp2.getInt(1), temp2.getInt(2), temp2.getString(3)
                    );
                }

                checkedClient.setSubscription(tempSubscription);
                checkedClient.setNextPaymentAt(LocalDateTime.parse(
                    temp.getString(7), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                   
            );
        }

        return checkedClient;   
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

    @Override
    public String toString() {
        return 
            "Client [id=" + this.getId() + ", email=" + this.getEmail() + ", lastName=" + this.getLastName() + 
                ", firstName=" + this.getFirstName() + ", hasActiveSub=" + this.isSubscribed() + 
                ", subscription=" + this.getSubscription().toString() + ", nextPaymentAt=" + this.getNextPaymentAt() + 
            "]";
    }
}
