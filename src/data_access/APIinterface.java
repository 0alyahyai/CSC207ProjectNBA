package data_access;

import entity.Player;
import entity.Stats;

import java.util.List;
import java.util.Map;

public interface APIinterface  {


    /**
     * This method returns general information of players that (loosely) match the name
     * @param name
     * @return Map<String, Object>
     */
    public  Map<String, Object> searchPlayer(String name);


    /**
     * This method is responsible for getting the stats of a player in the current season
     * @param id
     * @return Map<String, Object>
     */
    public Map<String, Object> getPlayerStats(int id);

    Stats getStats();

    String getNameOfPlayer(int id);


    //this method may change
    public String[] viewTeamGetStats(Player player);


    List<Player> getAllPlayersByName();

}
