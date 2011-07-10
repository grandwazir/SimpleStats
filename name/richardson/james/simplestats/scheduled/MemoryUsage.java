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

package name.richardson.james.simplestats.scheduled;

import name.richardson.james.simplestats.persistant.MemoryStatusRecord;

public class MemoryUsage implements Runnable {

	private final static long tickPerSecond = 20;
	private final static long repeatTimeInMilliseconds = 300000; 
	
	public final static long repeatTimeInTicks = (repeatTimeInMilliseconds / 1000) * tickPerSecond;
	
	public void run() {
        Runtime runtime = Runtime.getRuntime();
        final int mb = 1024 * 1024;
        
        int maxMemory = (int)runtime.maxMemory() / mb;
        int totalMemory = (int)runtime.totalMemory() / mb;
        int freeMemory = (int)runtime.freeMemory() / mb;
        int usedMemory = (totalMemory - freeMemory);
        // create the record
        MemoryStatusRecord record = new MemoryStatusRecord();
        record.setCreatedAt(System.currentTimeMillis());
        record.setMemoryMax(maxMemory);
        record.setMemoryTotal(totalMemory);
        record.setMemoryUsed(usedMemory);
        record.setMemoryFree(freeMemory);
        record.save();
    }
}