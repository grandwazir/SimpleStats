/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * MemoryStatusRecord.java is part of SimpleStats.
 * 
 * SimpleStats is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * SimpleStats is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * SimpleStats. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package name.richardson.james.bukkit.simplestats.memory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "simplestats_memory")
public class MemoryStatusRecord {

  @Id
  private long createdAt;

  @NotNull
  private int memoryTotal;

  @NotNull
  private int memoryUsed;

  @NotNull
  private int memoryFree;

  @NotNull
  private int memoryMax;

  public long getCreatedAt() {
    return this.createdAt;
  }

  public int getMemoryFree() {
    return this.memoryFree;
  }

  public int getMemoryMax() {
    return this.memoryMax;
  }

  public int getMemoryTotal() {
    return this.memoryTotal;
  }

  public int getMemoryUsed() {
    return this.memoryUsed;
  }

  public void setCreatedAt(final long createdAt) {
    this.createdAt = createdAt;
  }

  public void setMemoryFree(final int memoryFree) {
    this.memoryFree = memoryFree;
  }

  public void setMemoryMax(final int memoryMax) {
    this.memoryMax = memoryMax;
  }

  public void setMemoryTotal(final int memoryTotal) {
    this.memoryTotal = memoryTotal;
  }

  public void setMemoryUsed(final int memoryUsed) {
    this.memoryUsed = memoryUsed;
  }

}
