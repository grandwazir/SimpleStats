package name.richardson.james.bukkit.simplestats;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import name.richardson.james.bukkit.utilities.metrics.AbstractMetricsListener;

public class MetricsListener extends AbstractMetricsListener {

  public MetricsListener(JavaPlugin plugin) throws IOException {
    super(plugin);
    this.metrics.start();
  }

}
