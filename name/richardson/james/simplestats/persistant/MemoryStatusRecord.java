/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * MemoryStatusRecord.java is part of SimpleStats.
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

package name.richardson.james.simplestats.persistant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name = "simplestats_memory")
public class MemoryStatusRecord extends Record {

	@Id
    private long createdAt;
	
	@NotNull
    private int memoryTotal;
    
    @NotNull
    private int memoryUsed;
    
    @NotNull
    private int memoryFree;
    
    @NotNull
    private int memoryMax;

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setMemoryUsed(int memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	public int getMemoryUsed() {
		return memoryUsed;
	}

	public void setMemoryFree(int memoryFree) {
		this.memoryFree = memoryFree;
	}

	public int getMemoryFree() {
		return memoryFree;
	}

	public void setMemoryTotal(int memoryTotal) {
		this.memoryTotal = memoryTotal;
	}

	public int getMemoryTotal() {
		return memoryTotal;
	}

	public void setMemoryMax(int memoryMax) {
		this.memoryMax = memoryMax;
	}

	public int getMemoryMax() {
		return memoryMax;
	}
}
