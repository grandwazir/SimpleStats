package name.richardson.james.simplestats.persistant;

import name.richardson.james.simplestats.SimpleStats;

public class Record {

	public void destroy() {
		SimpleStats.getDb().delete(this);
	}
	
	public void save() {
		SimpleStats.getDb().save(this);
	}
	
}
