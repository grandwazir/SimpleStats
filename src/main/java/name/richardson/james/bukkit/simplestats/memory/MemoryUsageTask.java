/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * MemoryUsageTask.java is part of SimpleStats.
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
package name.richardson.james.bukkit.simplestats.memory;

import name.richardson.james.bukkit.simplestats.DatabaseHandler;

public class MemoryUsageTask implements Runnable {

  private final DatabaseHandler handler;

  public MemoryUsageTask(final DatabaseHandler handler) {
    this.handler = handler;
  }

  public void run() {
    final Runtime runtime = Runtime.getRuntime();
    final int mb = 1024 * 1024;
    final int maxMemory = (int) runtime.maxMemory() / mb;
    final int totalMemory = (int) runtime.totalMemory() / mb;
    final int freeMemory = (int) runtime.freeMemory() / mb;
    final int usedMemory = (totalMemory - freeMemory);
    // create the record
    final MemoryStatusRecord record = new MemoryStatusRecord();
    record.setCreatedAt(System.currentTimeMillis());
    record.setMemoryMax(maxMemory);
    record.setMemoryTotal(totalMemory);
    record.setMemoryUsed(usedMemory);
    record.setMemoryFree(freeMemory);
    this.handler.save(record);
  }

}
