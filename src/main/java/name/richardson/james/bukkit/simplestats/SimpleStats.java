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
package name.richardson.james.bukkit.simplestats;

import name.richardson.james.bukkit.simplestats.player.PlayerCountRecord;
import name.richardson.james.bukkit.utilities.internals.Logger;
import name.richardson.james.bukkit.utilities.plugin.SimplePlugin;

import java.io.IOException;
import java.sql.SQLException;
import javax.persistence.PersistenceException;
 
public class SimpleStats extends SimplePlugin {
 
  private DatabaseHandler databaseHandler;
  private SimpleStatsConfiguration configuration;
    
	public DatabaseHandler getDatabaseHandler() {
    return this.databaseHandler;
  }
    
  public void onDisable(){
		this.getServer().getScheduler().cancelTasks(this);
	}
  
  public void onEnable(){
		
    try {
      logger.setPrefix("[SimpleStats] ");
      this.setResourceBundle();
      loadConfiguration();
      setupDatabase();
    } catch (IOException e) {
      this.logger.severe("Unable to create configuration!");
      this.setEnabled(false);
    } catch (SQLException e) {
      this.logger.severe("Unable to initalise database!");
      e.printStackTrace();
    } finally {
      if (!this.isEnabled()) return;
    }
    
	}
    
	private void loadConfiguration() throws IOException {
    configuration = new SimpleStatsConfiguration(this);
    if (configuration.isDebugging()) {
      Logger.setDebugging(this, true);
    } 
  }

  private void setupDatabase() throws SQLException {
    try {
      getDatabase().find(PlayerCountRecord.class).findRowCount();
    } catch (final PersistenceException ex) {
      logger.warning("No database schema found; making a new one.");
      installDDL();
    }
    databaseHandler = new DatabaseHandler(getDatabase());
	}
	
}
