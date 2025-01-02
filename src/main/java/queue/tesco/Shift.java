package queue.tesco;

import lombok.Builder;
import lombok.Data;

/** This is independent of Employee shift worked anytime */
@Data
@Builder(toBuilder = true)
public class Shift {
  private int id; // TODO UUID
  private Skill skill;
  private int totalDurationMinutes;
  private int breakDurationMinutes;
}
