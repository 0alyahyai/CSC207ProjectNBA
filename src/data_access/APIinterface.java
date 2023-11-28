package data_access;

import entity.Player;
import entity.Stats;

public interface APIinterface {

    public Stats getStats();

    //this method may change
    public String[] viewTeamGetStats(Player player);

}
