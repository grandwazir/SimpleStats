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

package name.richardson.james.simplestats.scheduled;

import java.util.List;
import java.util.logging.Level;

import name.richardson.james.simplestats.SimpleStats;
import name.richardson.james.simplestats.persistant.PlayerCountAverageRecord;
import name.richardson.james.simplestats.persistant.PlayerCountRecord;

public class AveragePlayerCount implements Runnable {
	
	private final static long tickPerSecond = 20;
	private final static long repeatTimeInMilliseconds = 600000; 
	
	public final static long repeatTimeInTicks = (repeatTimeInMilliseconds / 1000) * tickPerSecond;
	
	public void run() {
        long endTime = System.currentTimeMillis();
        long startTime = endTime - AveragePlayerCount.repeatTimeInMilliseconds;
        int average = 0;
        List<PlayerCountRecord> records = PlayerCountRecord.findAllBetween(startTime, endTime);

   	 	// Get all the records made in the last 5 minutes and average the values
        for (PlayerCountRecord record : records) {
        	average = average + record.getPlayerCount();
        	SimpleStats.log(Level.INFO, Integer.toString(record.getPlayerCount()));
        }
        
        // If no one has left or joined in the last 5 minutes use the current player count instead.
        if (average != 0) {
        	average = Math.round(average / records.size());
        } else {
        	average = SimpleStats.getPlayerCount();
        }
        
        // create a new record
        PlayerCountAverageRecord record = new PlayerCountAverageRecord();
        record.setCreatedAt(startTime);
        record.setPlayerCount(average);
        record.setPlayerMax(SimpleStats.getMaxPlayerCount());
        record.save();
    }
	
}