package queue.tesco;

/** Cross reference between {@link Employee} and his payment */
public record EmployeePayment(Employee employee, double payment) {}
