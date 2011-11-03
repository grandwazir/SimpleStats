/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * SimpleStats.java is part of SimpleStats.
 * 
 * SimpleStats is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 * 
 * SimpleStats is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with SimpleStats.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package name.richardson.james.simplestats;

import name.richardson.james.simplestats.listeners.SimpleStatsPlayerListener;
import name.richardson.james.simplestats.persistant.MemoryStatusRecord;
import name.richardson.james.simplestats.persistant.PlayerCountRecord;
import name.richardson.james.simplestats.scheduled.MemoryUsage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.avaje.ebean.EbeanServer;
 
public class SimpleStats extends JavaPlugin {
 
	private final static Logger logger = Logger.getLogger("Minecraft");
	
	private PluginManager pm;
  private PluginDescriptionFile desc;
  private BukkitScheduler scheduler;
    
  private static EbeanServer db;
	private static SimpleStats instance;
    
  private final SimpleStatsPlayerListener playerListener;
    
  public SimpleStats() {
    SimpleStats.instance = this;
    this.playerListener = new SimpleStatsPlayerListener();
  }
    
	public static EbeanServer getDb() {
    return db;
  }
 
	public static int getMaxPlayerCount() {
    return SimpleStats.instance.getServer().getMaxPlayers();
  }
	
  public static int getPlayerCount() {
    return SimpleStats.instance.getServer().getOnlinePlayers().length;
  }
	
  public static void log(Level level, String msg) {
    logger.log(level, "[SimpleStats] " + msg);
  }
    
  @Override
  public List<Class<?>> getDatabaseClasses() {
    List<Class<?>> list = new ArrayList<Class<?>>();
    list.add(PlayerCountRecord.class);
    list.add(MemoryStatusRecord.class);
    return list;
  }
    
  public String getName() {
    return desc.getName();
  }
    
  public String getVersion() {
    return desc.getVersion();
  }
    
  public void onDisable(){
		scheduler.cancelTasks(this);
		log(Level.INFO, desc.getFullName() + " has been disabled");
	}
  
  public void onEnable(){
    desc = getDescription();
		db = getDatabase();
		scheduler = getServer().getScheduler();
		
		setupDatabase();
		
		// Register our events
		pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Monitor, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Monitor, this);
		
		// Start scheduled tasks
		scheduler.scheduleSyncRepeatingTask(this, new MemoryUsage(), 20, MemoryUsage.repeatTimeInTicks);
		log(Level.INFO, desc.getFullName() + " has been enabled");
	}
    
	private void setupDatabase() {
		try {
		  getDatabase().find(PlayerCountRecord.class).findRowCount();
      getDatabase().find(MemoryStatusRecord.class).findRowCount();
    } catch (PersistenceException ex) {
      log(Level.WARNING, "No database found, creating schema.");
      installDDL();
    }
	}
	
}
