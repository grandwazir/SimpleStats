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

package name.richardson.james.simplestats;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "ss_players")

public class SimpleStatsPlayerRecord {

	@Id
    private long time;
	
	@NotNull
    private int playerCount;
    
    @NotNull
    private int playerMax;
    
    public long getTime() {
    	return time;
    }

    public void setTime(long time) {
    	this.time = time;
    }

    public int getPlayerCount() {
    	return playerCount;
    }

    public void setPlayerCount(int amount) {
    	this.playerCount = amount;
    }
    
    public int getPlayerMax() {
    	return playerMax;
    }

    public void setPlayerMax(int amount) {
    	this.playerMax = amount;
    }

	
}
