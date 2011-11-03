/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * Record.java is part of SimpleStats.
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

package name.richardson.james.simplestats.persistant;

import javax.persistence.Entity;
import javax.persistence.Table;

import name.richardson.james.simplestats.SimpleStats;

@Entity()
@Table(name = "simplestats")
public class Record {

  public void destroy() {
    SimpleStats.getDb().delete(this);
  }

  public void save() {
    SimpleStats.getDb().save(this);
  }

}
