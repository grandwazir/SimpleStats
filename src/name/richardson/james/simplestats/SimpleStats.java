package name.richardson.james.simplestats;

import name.richardson.james.simplestats.SimpleStatsPlayerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
 
public class SimpleStats extends JavaPlugin {
 
	// Listeners
	private final SimpleStatsPlayerListener PlayerListener = new SimpleStatsPlayerListener(this);
	
	Logger log = Logger.getLogger("Minecraft");
 
	public void onEnable(){
		log.info("SimpleStats has been enabled!");
		setupDatabase();
		// Enable events
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN, PlayerListener, Event.Priority.Monitor, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, PlayerListener, Event.Priority.Monitor, this);

	}
 
	public void onDisable(){
		log.info("SimpleStats has been disabled.");
	}
	
	private void setupDatabase() {
		try {
            getDatabase().find(SimpleStatsPlayerRecord.class).findRowCount();
        } catch (PersistenceException ex) {
        	installDDL();
        }
	}
	
    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(SimpleStatsPlayerRecord.class);
        return list;
    }
    
}
