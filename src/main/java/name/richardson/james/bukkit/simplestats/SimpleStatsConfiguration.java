/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * SimpleStatsConfiguration.java is part of SimpleStats.
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

import name.richardson.james.bukkit.utilities.configuration.PluginConfiguration;
import name.richardson.james.bukkit.utilities.formatters.TimeFormatter;
import name.richardson.james.bukkit.utilities.plugin.SkeletonPlugin;

public class SimpleStatsConfiguration extends PluginConfiguration {

  public SimpleStatsConfiguration(final SkeletonPlugin plugin) throws IOException {
    super(plugin);
  }

  public long getMemoryUsageTrackingInterval() {
    return TimeFormatter.parseTime(this.configuration.getString("memory-tracking.interval", "5m"));
  }

  public long getPerformaceTrackingInterval() {
    return TimeFormatter.parseTime(this.configuration.getString("performance-tracking.interval", "5m"));
  }

  public boolean isMemoryUsageTrackingEnabled() {
    return this.configuration.getBoolean("memory-tracking.enabled");
  }

  public boolean isPerformanceTrackingEnabled() {
    return this.configuration.getBoolean("performance-tracking.enabled");
  }

  public boolean isPlayerCountTrackingEnabled() {
    return this.configuration.getBoolean("player-count-tracking.enabled");
  }

}
