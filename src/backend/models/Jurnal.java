package backend.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import backend.DatabaseManager;

public class Jurnal extends Model{
    
    private Integer         id;
    private Client          client;
    private Integer         event_id;
    private LocalDateTime   created_at;
    private String          descr;

    public Integer       getId()                { return id; }
    public String        getDescr()             { return descr; }
    public Client        getClient()            { return client; }
    public Integer       getEvent()             { return event_id; }
    public LocalDateTime getCreated_at()        { return created_at; }

    public Jurnal() {
        settableName("jurnals");
    }

    public Jurnal(Integer id, Client client, Integer event, LocalDateTime created_at) {
        settableName("jurnals");
        this.id = id;
        this.client = client;
        this.event_id = event;
        this.created_at = created_at; 
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add(event_id.toString());
        try {
            ResultSet rs = new DatabaseManager().selectPreparedSQL("select descr from events where id = ? LIMIT 1", parameters);
            rs.next();

            this.descr = rs.getString(1);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }


    public static Jurnal getDetails(Integer id) throws SQLException {
        ResultSet temp = new Jurnal().find(id);

        return temp != null ? 
        new Jurnal(
            temp.getInt(1), Client.getDetails(temp.getInt(2)), temp.getInt(3), 
            LocalDateTime.parse(
                temp.getString(4),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        ) : null ;
    }
}
