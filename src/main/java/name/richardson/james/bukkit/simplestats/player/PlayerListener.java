/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * PlayerListener.java is part of SimpleStats.
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
package name.richardson.james.bukkit.simplestats.player;

import com.avaje.ebean.EbeanServer;

import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import name.richardson.james.bukkit.simplestats.SimpleStats;

public class PlayerListener implements Listener {

  private final Server server;
  private final EbeanServer database;

  public PlayerListener(final SimpleStats plugin) {
    this.server = plugin.getServer();
    this.database = plugin.getDatabase();
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onPlayerJoin(final PlayerJoinEvent event) {
    this.onPlayerCountChanged(this.getPlayerCount());
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onPlayerQuit(final PlayerQuitEvent event) {
    this.onPlayerCountChanged(this.getPlayerCount() - 1);
  }

  private int getMaxPlayers() {
    return this.server.getMaxPlayers();
  }

  private int getPlayerCount() {
    return this.server.getOnlinePlayers().length;
  }

  private void onPlayerCountChanged(final int playerCount) {
    final PlayerCountRecord record = new PlayerCountRecord();
    record.setPlayerCount(playerCount);
    record.setPlayerMax(this.getMaxPlayers());
    this.database.save(record);
  }

}
