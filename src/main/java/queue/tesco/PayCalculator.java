package queue.tesco;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tesco wants to implement a skill-based pay system to reward employees for their specialised
 * skills.
 *
 * <p>This system will provide differential pay in addition to their regular contracted pay.
 *
 * <p>In the first phase, Tesco is only paying based on the contract and the skill they worked on.
 *
 * <p>Write a Skill-based pay calculation system to calculate the total pay for a week for the given
 * colleagues.
 *
 * <p>> As a future enhancement, Tesco plans to introduce different rates for overtime hours.
 *
 * <p>Input You will be provided with a list of employees, each with the following information: ·
 * Employee id · List of shifts worked. · Contract Type
 *
 * <p>Each shift will have the following information: · Shift id · Skill · Total Duration · Break
 * duration
 *
 * <p>Skill Pay Rate Bakery £2/hour Checkout Cashier £1.2/hour Customer Service £1.3/hour Shift
 * Leader £3/hour Security £1/hour Cleaning £1/hour Delivery Driver £2/hour
 *
 * <p>Contract type Pay Rate Permanent £12.5/hour Agency £13/hour Zero-hours £13/hour
 */
public class PayCalculator {

  public List<EmployeePayment> calculatePayments(List<EmployeeShift> employeeShift) {

    return employeeShift.stream().map(es -> calculatePayment(es)).collect(Collectors.toList());
  }

  private EmployeePayment calculatePayment(EmployeeShift es) {
    var employee = es.employee();
    var shift = es.shift();
    var totalPayment =
        calculateContractPaymentValue(employee, shift) + calculateSkillPaymentValue(shift);
    return new EmployeePayment(employee, totalPayment);
  }

  private double calculateContractPaymentValue(Employee employee, List<Shift> shift) {

    return shift.stream()
        .mapToDouble(
            shift1 ->
                employee.contractType().payment
                    * ((shift1.getTotalDurationMinutes() - shift1.getBreakDurationMinutes()) / 60))
        .sum();
  }

  private double calculateSkillPaymentValue(List<Shift> shift) {

    return shift.stream()
        .mapToDouble(
            shift1 ->
                shift1.getSkill().payment
                    * ((shift1.getTotalDurationMinutes() - shift1.getBreakDurationMinutes()) / 60))
        .sum();
  }
}
