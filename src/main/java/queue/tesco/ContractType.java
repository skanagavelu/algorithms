package queue.tesco;

/** Employee Work Contracts */
public enum ContractType {
  PERMANENT(1),
  AGENCY(1),
  ZERO_HOURS(1);

  public double payment;

  private ContractType(double payment) {
    this.payment = payment;
  }
}
