package queue.tesco;

/** Work Type Skills for an {@link Employee} */
public enum Skill {
  BAKERY(1),
  CASHIER(2),
  CUSTOMER_SERVICE(3),
  SHIFT_LEADER(4),
  SECURITY(5),
  CLEANING(6),
  DELIVERY_DRIVER(7);

  public double payment;

  Skill(double payment) {
    this.payment = payment;
  }
}
