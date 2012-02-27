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

package name.richardson.james.bukkit.simplestats.listeners;

import name.richardson.james.bukkit.simplestats.SimpleStats;
import name.richardson.james.bukkit.simplestats.persistant.PlayerCountRecord;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SimpleStatsPlayerListener extends PlayerListener {

  public void onPlayerJoin(PlayerJoinEvent event) {
    onPlayerCountChanged(SimpleStats.getPlayerCount());
  }

  public void onPlayerQuit(PlayerQuitEvent event) {
    onPlayerCountChanged(SimpleStats.getPlayerCount() - 1);
  }

  private void onPlayerCountChanged(int playerCount) {
    PlayerCountRecord record = new PlayerCountRecord();
    record.setPlayerCount(playerCount);
    record.setPlayerMax(SimpleStats.getMaxPlayerCount());
    record.save();
  }
}
