package name.richardson.james.simplestats.persistant;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity()
@Table(name = "simplestats_average_players")
public class PlayerCountAverageRecord extends PlayerCountRecord {

}
