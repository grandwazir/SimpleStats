package name.richardson.james.bukkit.simplestats;

import java.io.IOException;

import name.richardson.james.bukkit.utilities.configuration.AbstractConfiguration;
import name.richardson.james.bukkit.utilities.plugin.SimplePlugin;

public class SimpleStatsConfiguration extends AbstractConfiguration {

  public SimpleStatsConfiguration(final SimplePlugin plugin) throws IOException {
    super(plugin, "config.yml");
  }
  
  public boolean isDebugging() {
    return configuration.getBoolean("debugging");
  }
  
}
