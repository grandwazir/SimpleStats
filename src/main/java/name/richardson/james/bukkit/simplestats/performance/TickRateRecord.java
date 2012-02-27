/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * TickRateRecord.java is part of SimpleStats.
 * 
 * SimpleStats is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * SimpleStats is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with SimpleStats.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package name.richardson.james.bukkit.simplestats.performance;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity()
@Table(name = "simplestats_performance")
public class TickRateRecord {

  private long createdAt;

  private float tickRate;

  public long getCreatedAt() {
    return this.createdAt;
  }

  public float getTickRate() {
    return this.tickRate;
  }

  public void setCreatedAt(final long createdAt) {
    this.createdAt = createdAt;
  }

  public void setTickRate(final float tickRate) {
    this.tickRate = tickRate;
  }

}
