/* 
Copyright 2011 James Richardson.

This file is part of SimpleStats.

SimpleStats is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SimpleStats is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with SimpleStats.  If not, see <http://www.gnu.org/licenses/>.
*/

package name.richardson.james.simplestats;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SimpleStatsPlayerListener extends PlayerListener {
	private SimpleStats plugin;

	public SimpleStatsPlayerListener(SimpleStats plugin) {
		this.plugin = plugin;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		playerCountChanged();
    }
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		playerCountChanged(); 
    }
	
	private void playerCountChanged() {
		int player_count = plugin.getServer().getOnlinePlayers().length;
        int max_players = plugin.getServer().getMaxPlayers();
        // Set attributes
        SimpleStatsPlayerRecord record = new SimpleStatsPlayerRecord();
        record.setTime(System.currentTimeMillis());
        record.setPlayerCount(player_count);
        record.setPlayerMax(max_players);
        // Save record
        plugin.getDatabase().save(record);
	}
	
}