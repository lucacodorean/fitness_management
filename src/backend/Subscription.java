package backend;

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

  public void setPrice(Integer price) {
    this.price = price;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Subscription() {
    this.settableName("subscriptions");
  }

  public Subscription(Integer id, Integer price, String description) {
    this.setId(id);
    this.setPrice(price);
    this.setDescription(description);

    this.settableName("subscriptions");
  }

  @Override
  public String toString() {
    return String.format(
      "Subscription [id=%s, price=%s, description=%s]",
      this.getId(),
      this.getPrice(),
      this.getDescription()
    );
  }
}
