package name.richardson.james.bukkit.simplestats;

import java.io.IOException;

import name.richardson.james.bukkit.utilities.configuration.AbstractConfiguration;
import name.richardson.james.bukkit.utilities.formatters.TimeFormatter;
import name.richardson.james.bukkit.utilities.plugin.SimplePlugin;

public class SimpleStatsConfiguration extends AbstractConfiguration {

  public SimpleStatsConfiguration(final SimplePlugin plugin) throws IOException {
    super(plugin, "config.yml");
  }
  
  public boolean isDebugging() {
    return configuration.getBoolean("debugging");
  }
  
  public boolean isMemoryUsageTrackingEnabled() {
    return configuration.getBoolean("memory-tracking.enabled");
  }
  
  public long getMemoryUsageTrackingInterval() {
    return TimeFormatter.parseTime(configuration.getString("memory-tracking.interval", "5m")); 
  }
  
  public boolean isPlayerCountTrackingEnabled() {
    return configuration.getBoolean("player-count-tracking.enabled");
  }
  
}
