package queue.tesco;

import java.util.List;

/** Cross reference between {@link Employee} and {@link Shift} */
public record EmployeeShift(Employee employee, List<Shift> shift) {}
