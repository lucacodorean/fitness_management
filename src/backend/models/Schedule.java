package backend.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule extends Model {
    private Integer         id;
    private Client          client; 
    private Employee         trainer;
    private LocalDateTime   timeEnd;
    private LocalDateTime   timeStart;

    public void setId(Integer id)                       { this.id = id; }
    public void setClient(Client client)                { this.client = client; }
    public void setTrainer(Employee trainer)             { this.trainer = trainer; }
    public void setTimeEnd(LocalDateTime timeEnd)       { this.timeEnd = timeEnd; }
    public void setTimeStart(LocalDateTime timeStart)   { this.timeStart = timeStart; }

    public Integer getId()              { return this.id; }
    public Client  getClient()          { return this.client; }
    public Employee getTrainer()         { return this.trainer; }
    public LocalDateTime getTimeEnd()   { return this.timeEnd; }
    public LocalDateTime getTimeStart() { return this.timeStart; }

    private Schedule () {
        this.settableName("schedule");
    }

    public Schedule(Integer id, Client client, Employee trainer, LocalDateTime timeStart, LocalDateTime timeEnd) {
        this.setId(id);
        this.setClient(client);
        this.setTrainer(trainer);
        this.setTimeStart(timeStart);
        this.setTimeEnd(timeEnd);

        this.settableName("schedule");
    }

    @Override
    public String toString() {
        return 
        "Schedule [id=" + this.getId() + ", client=" + this.getClient().toString() + 
            ", trainer=" + this.getTrainer().toString() + ", timeStart=" + this.getTimeStart() +
            ", timeEnd=" + this.getTimeEnd() + "]";
    }

    public static Schedule getDetails(Integer id) throws SQLException {
        ResultSet temp = new Schedule().find(id);

        return temp != null ? new Schedule(temp.getInt(1), 
            Client.getDetails(temp.getInt(2)), Employee.getDetails(temp.getInt(3)),
            LocalDateTime.parse( temp.getString(4), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            LocalDateTime.parse( temp.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        ) : null;
    }
}
