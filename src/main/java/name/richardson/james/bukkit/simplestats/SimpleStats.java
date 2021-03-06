/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * SimpleStats.java is part of SimpleStats.
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
package name.richardson.james.bukkit.simplestats;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.avaje.ebean.EbeanServer;

import name.richardson.james.bukkit.simplestats.memory.MemoryStatusRecord;
import name.richardson.james.bukkit.simplestats.memory.MemoryUsageTask;
import name.richardson.james.bukkit.simplestats.performance.TickPerSecondMeasurementTask;
import name.richardson.james.bukkit.simplestats.performance.TickRateRecord;
import name.richardson.james.bukkit.simplestats.player.PlayerCountRecord;
import name.richardson.james.bukkit.simplestats.player.PlayerListener;
import name.richardson.james.bukkit.utilities.configuration.DatabaseConfiguration;
import name.richardson.james.bukkit.utilities.persistence.SQLStorage;
import name.richardson.james.bukkit.utilities.plugin.AbstractPlugin;

public class SimpleStats extends AbstractPlugin {

  private SQLStorage storage;
  private SimpleStatsConfiguration configuration;
  private PlayerListener playerListener;

  private int MemoryUsageTaskId;
  private int TickPerSecondMeasurementTaskId;

  public void enableMemoryUsageTracking(final boolean status) {
    if (status) {
      final MemoryUsageTask task = new MemoryUsageTask(this.storage.getEbeanServer());
      final long tickInterval = (this.configuration.getMemoryUsageTrackingInterval() / 1000) * 20;
      this.MemoryUsageTaskId = this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, task, 0, tickInterval);
    } else {
      if (this.MemoryUsageTaskId != 0) {
        this.getServer().getScheduler().cancelTask(this.MemoryUsageTaskId);
        this.MemoryUsageTaskId = 0;
      }
    }
  }

  public void enablePerformanceTracking(final boolean status) {
    if (status) {
      final TickPerSecondMeasurementTask task = new TickPerSecondMeasurementTask(this);
      final long tickInterval = (this.configuration.getPerformaceTrackingInterval() / 1000) * 20;
      this.TickPerSecondMeasurementTaskId = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, task, 0, tickInterval);
    } else {
      if (this.TickPerSecondMeasurementTaskId != 0) {
        this.getServer().getScheduler().cancelTask(this.TickPerSecondMeasurementTaskId);
        this.TickPerSecondMeasurementTaskId = 0;
      }
    }
  }

  public void enablePlayerCountTracking(final boolean status) {
    if (status) {
      this.playerListener = new PlayerListener(this);
      this.getServer().getPluginManager().registerEvents(this.playerListener, this);
    } else {
    }
  }

  @Override
  public List<Class<?>> getDatabaseClasses() {
    List<Class<?>> list =  new LinkedList<Class<?>>();
    list.add(MemoryStatusRecord.class);
    list.add(TickRateRecord.class);
    list.add(PlayerCountRecord.class);
    return list;
  }

  public EbeanServer getDatabase() {
    return this.storage.getEbeanServer();
  }

  public SimpleStatsConfiguration getSimpleStatsConfiguration() {
    return this.configuration;
  }

  @Override
  public void onDisable() {
    this.enableMemoryUsageTracking(false);
    this.enablePlayerCountTracking(false);
  }

  protected void loadConfiguration() throws IOException {
    super.loadConfiguration();
    this.configuration = new SimpleStatsConfiguration(this);
  }

  protected void establishPersistence() throws SQLException {
    try {
      this.storage = new SQLStorage(this, new DatabaseConfiguration(this), this.getDatabaseClasses());
      this.storage.initalise();
      this.enableTracking();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void enableTracking() {
    if (this.configuration.isPlayerCountTrackingEnabled()) {
      this.enablePlayerCountTracking(true);
    }
    if (this.configuration.isMemoryUsageTrackingEnabled()) {
      this.enableMemoryUsageTracking(true);
    }
    if (this.configuration.isPerformanceTrackingEnabled()) {
      this.enablePerformanceTracking(true);
    }
  }

  public String getGroupID() {
    return "name.richardson.james.bukkit";
  }

  public String getArtifactID() {
    return "simple-stats";
  }

}
