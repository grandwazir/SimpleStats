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
