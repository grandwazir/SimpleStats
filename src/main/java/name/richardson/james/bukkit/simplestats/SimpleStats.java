/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * SimpleStats.java is part of SimpleStats.
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
package name.richardson.james.bukkit.simplestats;

import name.richardson.james.bukkit.simplestats.memory.MemoryUsageTask;
import name.richardson.james.bukkit.simplestats.performance.TickPerSecondMeasurementTask;
import name.richardson.james.bukkit.simplestats.player.PlayerCountRecord;
import name.richardson.james.bukkit.simplestats.player.PlayerListener;
import name.richardson.james.bukkit.utilities.internals.Logger;
import name.richardson.james.bukkit.utilities.plugin.SimplePlugin;

import java.io.IOException;
import java.sql.SQLException;
import javax.persistence.PersistenceException;
 
public class SimpleStats extends SimplePlugin {
 
  private DatabaseHandler databaseHandler;
  private SimpleStatsConfiguration configuration;
  private PlayerListener playerListener;
  
  private int MemoryUsageTaskId;
  private int TickPerSecondMeasurementTaskId; 
    
	public DatabaseHandler getDatabaseHandler() {
    return this.databaseHandler;
  }
    
  public void onDisable(){
		this.enableMemoryUsageTracking(false);
		this.enablePlayerCountTracking(false);
		this.getSimpleFormattedMessage("plugin-disabled", this.getDescription().getName());
	}
  
  public void onEnable(){
		
    try {
      logger.setPrefix("[SimpleStats] ");
      this.setResourceBundle();
      loadConfiguration();
      setupDatabase();
      if (configuration.isPlayerCountTrackingEnabled()) this.enablePlayerCountTracking(true);
      if (configuration.isMemoryUsageTrackingEnabled()) this.enableMemoryUsageTracking(true);
      if (configuration.isPerformaceTrackingEnabled()) this.enablePerformanceTracking(true);
    } catch (IOException e) {
      this.logger.severe(this.getMessage("unable-to-create-configuration"));
      this.setEnabled(false);
    } catch (SQLException e) {
      this.logger.severe(this.getMessage("unable-to-use-database"));
      e.printStackTrace();
      this.setEnabled(false);
    } finally {
      if (!this.isEnabled()) {
        this.logger.severe(this.getMessage("panic"));
        return;
      }
    }
    
    this.getSimpleFormattedMessage("plugin-enabled", this.getDescription().getFullName());
    
	}
  
  public void enablePlayerCountTracking(boolean status) {
    if (status) {
      playerListener = new PlayerListener(this);
      this.getServer().getPluginManager().registerEvents(playerListener, this);
    } else {
      // TODO - Add unregistering events when method is added to Bukkit.
      // See http://forums.bukkit.org/threads/unregistering-of-events.60817
    }
  }
  
  public void enableMemoryUsageTracking(boolean status) {
    if (status) {
      MemoryUsageTask task = new MemoryUsageTask(this.databaseHandler);
      long tickInterval = (configuration.getMemoryUsageTrackingInterval() / 1000) * 20;
      MemoryUsageTaskId = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, task, 0, tickInterval);
    } else {
      if (MemoryUsageTaskId != 0) {
        this.getServer().getScheduler().cancelTask(MemoryUsageTaskId);
        this.MemoryUsageTaskId = 0;
      }
    }
  }
  
  public void enablePerformanceTracking(boolean status) {
    if (status) {
      TickPerSecondMeasurementTask task = new TickPerSecondMeasurementTask(this);
      long tickInterval = (configuration.getPerformaceTrackingInterval() / 1000) * 20;
      TickPerSecondMeasurementTaskId = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, task, 0, tickInterval);
    } else {
      if (TickPerSecondMeasurementTaskId != 0) {
        this.getServer().getScheduler().cancelTask(TickPerSecondMeasurementTaskId);
        this.TickPerSecondMeasurementTaskId = 0;
      }
    }
  }
  
  public SimpleStatsConfiguration getSimpleStatsConfiguration() {
    return this.configuration;
  }
  
	private void loadConfiguration() throws IOException {
    configuration = new SimpleStatsConfiguration(this);
    if (configuration.isDebugging()) {
      Logger.setDebugging(this, true);
    } 
  }

  private void setupDatabase() throws SQLException {
    try {
      getDatabase().find(PlayerCountRecord.class).findRowCount();
    } catch (final PersistenceException ex) {
      logger.warning(this.getMessage("no-database"));
      installDDL();
    }
    databaseHandler = new DatabaseHandler(getDatabase());
	}
	
}
