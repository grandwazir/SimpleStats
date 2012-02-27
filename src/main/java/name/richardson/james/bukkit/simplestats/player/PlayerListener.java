/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * SimpleStatsPlayerListener.java is part of SimpleStats.
 * 
 * SimpleStats is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * SimpleStats is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SimpleStats.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package name.richardson.james.bukkit.simplestats.player;

import name.richardson.james.bukkit.simplestats.DatabaseHandler;
import name.richardson.james.bukkit.simplestats.SimpleStats;

import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

  private final Server server;
  private final DatabaseHandler handler;
  
  public PlayerListener(final SimpleStats plugin) {
    this.server = plugin.getServer();
    this.handler = plugin.getDatabaseHandler();
  }
  
  @EventHandler(priority = EventPriority.MONITOR)
  public void onPlayerJoin(PlayerJoinEvent event) {
    onPlayerCountChanged(this.getPlayerCount());
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onPlayerQuit(PlayerQuitEvent event) {
    onPlayerCountChanged(this.getPlayerCount() - 1);
  }

  private void onPlayerCountChanged(int playerCount) {
    PlayerCountRecord record = new PlayerCountRecord();
    record.setPlayerCount(playerCount);
    record.setPlayerMax(this.getMaxPlayers());
    handler.save(record);
  }
  
  private int getMaxPlayers() {
    return this.server.getMaxPlayers();
  }
  
  private int getPlayerCount() {
    return this.server.getOnlinePlayers().length;
  }
  
}
