/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * PlayerCountRecord.java is part of SimpleStats.
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
package name.richardson.james.bukkit.simplestats.player;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "simplestats_players")
public class PlayerCountRecord {

  @Id
  private long createdAt;

  @NotNull
  private int playerCount;

  @NotNull
  private int playerMax;

  public PlayerCountRecord() {
    this.createdAt = System.currentTimeMillis();
  }

  public long getCreatedAt() {
    return this.createdAt;
  }

  public int getPlayerCount() {
    return this.playerCount;
  }

  public int getPlayerMax() {
    return this.playerMax;
  }

  public void setCreatedAt(final long createdAt) {
    this.createdAt = createdAt;
  }

  public void setPlayerCount(final int playerCount) {
    this.playerCount = playerCount;
  }

  public void setPlayerMax(final int playerMax) {
    this.playerMax = playerMax;
  }

}
