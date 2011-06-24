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