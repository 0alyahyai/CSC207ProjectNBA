package data_access;

import entity.Player;
import entity.Stats;

public interface APIInterface {

    public Stats getStats();

    //this method may change
    public String[] viewTeamGetStats(Player player);

}
