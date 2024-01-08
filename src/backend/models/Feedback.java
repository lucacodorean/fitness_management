package backend.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Feedback extends Model {
    private Integer     id;
    private Client      client;
    private Employee    employee;   
    private Float       rating;
    private String      description;

    public Feedback() {
        this.settableName("feedback");
    }

    public Feedback(Integer id, Client client, Employee employee, Float rating, String description) {
        this.setId(id);
        this.setClient(client);
        this.setRating(rating);
        this.setEmployee(employee);
        this.setDescription(description);

        this.settableName("feedback");
    }

    public void setId(Integer id)                        { this.id = id; }
    public void setRating(Float rating)                  { this.rating = rating; }
    public void setClient(Client client)                 { this.client = client; }
    public void setEmployee(Employee employee)           { this.employee = employee; }
    public void setDescription(String description)       { this.description = description; }

    public Integer  getId()          { return this.id; }
    public Client   getClient()      { return this.client; }
    public Float    getRating()      { return this.rating; }
    public Employee getEmployee()    { return this.employee; }
    public String   getDescription() { return this.description; }

    public static Feedback getDetails(Integer id) throws SQLException {
        ResultSet temp = new Feedback().find(id);

        return temp != null ? 
        new Feedback(
            temp.getInt(1),  Client.getDetails(temp.getInt(2)), Employee.getDetails(temp.getInt(3)),
            temp.getFloat(4), temp.getString(5)
        ) : null ;
    }
 
    @Override
    public String toString() {
        return "Feedback [id=" + this.getId() + ", client=" + this.getClient().toString() + 
            ", employee=" + this.getEmployee().toString() + ", rating=" + this.getRating().toString()
                + ", description=" + this.getDescription() + "]";
    }
}
