package data_access;

import entity.Player;
import entity.Stats;

import java.util.Map;

public interface APIinterface {

    Stats getStats();

    String getNameOfPlayer(int id);


    //this method may change
    public String[] viewTeamGetStats(Player player);

}
