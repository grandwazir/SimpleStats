/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * DatabaseHandler.java is part of BanHammer.
 * 
 * BanHammer is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * BanHammer is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * BanHammer. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package name.richardson.james.bukkit.simplestats;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.EbeanServer;

import name.richardson.james.bukkit.simplestats.memory.MemoryStatusRecord;
import name.richardson.james.bukkit.simplestats.performance.TickRateRecord;
import name.richardson.james.bukkit.simplestats.player.PlayerCountRecord;

public class DatabaseHandler extends name.richardson.james.bukkit.utilities.database.Database {

  public static List<Class<?>> getDatabaseClasses() {
    final List<Class<?>> list = new ArrayList<Class<?>>();
    list.add(MemoryStatusRecord.class);
    list.add(TickRateRecord.class);
    list.add(PlayerCountRecord.class);
    return list;
  }

  public DatabaseHandler(final EbeanServer database) throws SQLException {
    super(database);
  }

}
