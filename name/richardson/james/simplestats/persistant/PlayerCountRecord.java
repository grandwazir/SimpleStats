/* 
Copyright 2011 James Richardson.

This file is part of SimpleStats.

SimpleStats is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SimpleStats is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with SimpleStats.  If not, see <http://www.gnu.org/licenses/>.
*/

package name.richardson.james.simplestats.persistant;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import name.richardson.james.simplestats.SimpleStats;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "simplestats_players")
public class PlayerCountRecord extends Record {

	@Id
    private long createdAt;
	
	@NotNull
    private int playerCount;
    
    @NotNull
    private int playerMax;
    
    public PlayerCountRecord() {
    	this.createdAt = System.currentTimeMillis();
    }
    
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getCreatedAt() {
		return createdAt;
	}
    
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerMax(int playerMax) {
		this.playerMax = playerMax;
	}

	public int getPlayerMax() {
		return playerMax;
	}
	
	public static List<PlayerCountRecord> findAllBetween(long start, long end) {
		return SimpleStats.getDb().find(PlayerCountRecord.class).where().between("created_at", start, end).findList();
	}
	
}