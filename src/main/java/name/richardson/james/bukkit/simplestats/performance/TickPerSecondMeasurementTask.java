/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * MemoryUsage.java is part of SimpleStats.
 * 
 * SimpleStats is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 * 
 * SimpleStats is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with SimpleStats.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package name.richardson.james.bukkit.simplestats.performance;

import java.text.DecimalFormat;

import name.richardson.james.bukkit.simplestats.DatabaseHandler;
import name.richardson.james.bukkit.simplestats.SimpleStats;

public class TickPerSecondMeasurementTask implements Runnable {

  public static final int tickRate = 20;
  
  private final DatabaseHandler handler;
  
  // The time now 
  private long now;
  
  // The last time that this task was run
  private long then;
  
  // The total elapsed time from between each run
  private float elapsedTime;

  private final float interval;

  public TickPerSecondMeasurementTask(final SimpleStats plugin) {
    this.handler = plugin.getDatabaseHandler();
    this.interval = plugin.getSimpleStatsConfiguration().getPerformaceTrackingInterval();
  }
  
  public void run() {
    now = System.currentTimeMillis();
    elapsedTime = (now - then);
    if (elapsedTime != now) {
      float tps = (interval / elapsedTime) * tickRate;;
      TickRateRecord record = new TickRateRecord();
      record.setCreatedAt(now);
      record.setTickRate(roundTPS(tps));
      handler.save(record);
    }
    then = System.currentTimeMillis();
  }
  
  private float roundTPS(float tps) {
    DecimalFormat formatter = new DecimalFormat("#.##");
    return Float.parseFloat(formatter.format(tps));
  }
  
}
