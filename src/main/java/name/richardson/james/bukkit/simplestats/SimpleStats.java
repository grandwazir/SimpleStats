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
import java.util.List;

import javax.persistence.PersistenceException;

import name.richardson.james.bukkit.simplestats.memory.MemoryUsageTask;
import name.richardson.james.bukkit.simplestats.performance.TickPerSecondMeasurementTask;
import name.richardson.james.bukkit.simplestats.player.PlayerCountRecord;
import name.richardson.james.bukkit.simplestats.player.PlayerListener;
import name.richardson.james.bukkit.utilities.internals.Logger;
import name.richardson.james.bukkit.utilities.plugin.SimplePlugin;

public class SimpleStats extends SimplePlugin {

  private DatabaseHandler databaseHandler;
  private SimpleStatsConfiguration configuration;
  private PlayerListener playerListener;

  private int MemoryUsageTaskId;
  private int TickPerSecondMeasurementTaskId;

  public void enableMemoryUsageTracking(final boolean status) {
    if (status) {
      this.logger.debug("Enabling memory usage tracking.");
      final MemoryUsageTask task = new MemoryUsageTask(this.databaseHandler);
      final long tickInterval = (this.configuration.getMemoryUsageTrackingInterval() / 1000) * 20;
      this.MemoryUsageTaskId = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, task, 0, tickInterval);
    } else {
      if (this.MemoryUsageTaskId != 0) {
        this.logger.debug("Disabling memory usage tracking.");
        this.getServer().getScheduler().cancelTask(this.MemoryUsageTaskId);
        this.MemoryUsageTaskId = 0;
      }
    }
  }

  public void enablePerformanceTracking(final boolean status) {
    if (status) {
      this.logger.debug("Enabling performance tracking.");
      final TickPerSecondMeasurementTask task = new TickPerSecondMeasurementTask(this);
      final long tickInterval = (this.configuration.getPerformaceTrackingInterval() / 1000) * 20;
      this.TickPerSecondMeasurementTaskId = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, task, 0, tickInterval);
    } else {
      if (this.TickPerSecondMeasurementTaskId != 0) {
        this.logger.debug("Disabling performance tracking.");
        this.getServer().getScheduler().cancelTask(this.TickPerSecondMeasurementTaskId);
        this.TickPerSecondMeasurementTaskId = 0;
      }
    }
  }

  public void enablePlayerCountTracking(final boolean status) {
    if (status) {
      this.logger.debug("Enabling player count tracking.");
      this.playerListener = new PlayerListener(this);
      this.getServer().getPluginManager().registerEvents(this.playerListener, this);
    } else {
      this.logger.debug("Disabling player count tracking.");
      // TODO - Add unregistering events when method is added to Bukkit.
      // See http://forums.bukkit.org/threads/unregistering-of-events.60817
    }
  }

  @Override
  public List<Class<?>> getDatabaseClasses() {
    return DatabaseHandler.getDatabaseClasses();
  }

  public DatabaseHandler getDatabaseHandler() {
    return this.databaseHandler;
  }

  public SimpleStatsConfiguration getSimpleStatsConfiguration() {
    return this.configuration;
  }

  @Override
  public void onDisable() {
    this.enableMemoryUsageTracking(false);
    this.enablePlayerCountTracking(false);
    this.logger.info(this.getSimpleFormattedMessage("plugin-disabled", this.getDescription().getName()));
  }

  @Override
  public void onEnable() {

    try {
      this.logger.setPrefix("[SimpleStats] ");
      this.setResourceBundle();
      this.loadConfiguration();
      this.setupDatabase();
      if (this.configuration.isPlayerCountTrackingEnabled()) {
        this.enablePlayerCountTracking(true);
      }
      if (this.configuration.isMemoryUsageTrackingEnabled()) {
        this.enableMemoryUsageTracking(true);
      }
      if (this.configuration.isPerformanceTrackingEnabled()) {
        this.enablePerformanceTracking(true);
      }
    } catch (final IOException e) {
      this.logger.severe(this.getMessage("unable-to-create-configuration"));
      this.setEnabled(false);
    } catch (final SQLException e) {
      this.logger.severe(this.getMessage("unable-to-use-database"));
      e.printStackTrace();
      this.setEnabled(false);
    } finally {
      if (!this.isEnabled()) {
        this.logger.severe(this.getMessage("panic"));
        return;
      }
    }

    this.logger.info(this.getSimpleFormattedMessage("plugin-enabled", this.getDescription().getFullName()));

  }

  private void loadConfiguration() throws IOException {
    this.configuration = new SimpleStatsConfiguration(this);
    if (this.configuration.isDebugging()) {
      Logger.setDebugging(this, true);
    }
  }

  private void setupDatabase() throws SQLException {
    try {
      this.getDatabase().find(PlayerCountRecord.class).findRowCount();
    } catch (final PersistenceException ex) {
      this.logger.warning(this.getMessage("no-database"));
      this.installDDL();
    }
    this.databaseHandler = new DatabaseHandler(this.getDatabase());
  }

}
