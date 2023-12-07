package backend.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Subscription extends Model {

  private Integer id;
  private Integer price;
  private String description;

  public Integer getId() {
    return id;
  }

  public Integer getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public void setId(Integer id) {
    this.id = id;
  }

    @Override
    public String toString() {
        return "Subscription [id=" + this.getId() + ", price=" + this.getPrice() + 
        ", description=" + this.getDescription() + "]";
    }

    public static Subscription getDetails(Integer id) throws SQLException {
        ResultSet temp = new Subscription().find(id);
        
        return temp != null ?
            new Subscription(temp.getInt(1), temp.getInt(2), temp.getString(3)) : null;
    }
}
