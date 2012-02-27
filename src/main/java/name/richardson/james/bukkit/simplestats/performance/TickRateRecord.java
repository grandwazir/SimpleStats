package name.richardson.james.bukkit.simplestats.performance;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity()
@Table(name = "simplestats_performance")
public class TickRateRecord {

  private long createdAt;
  
  private float tickRate;

  public float getTickRate() {
    return tickRate;
  }

  public void setTickRate(float tickRate) {
    this.tickRate = tickRate;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }
  
  
  
}
