package data_access;

import use_case.entity_helpers.Stats;

import java.util.ArrayList;
import java.util.Map;

public interface APIinterface  {


    /**
     * This method returns general information of players that contain the String name in their names.
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


    /**
     * This method returns general info (i.e. name, biometrics, etc.) of a player in the current season
     * @return Stats
     */
    public Map<String, Object> getGeneralPlayerInfo(int id);


    //TODO: delete this method or change it
    Stats getStats();


    /**
     * This method returns the name of a player given their id
     * @param id
     * @return String
     */
    String getNameOfPlayer(int id);


    /**
     * This method returns the stats of a player that are required for the ViewTeam use case given their id
     * @param id
     * @return ArrayList<String>
     */
    public ArrayList<String> viewTeamGetStats(int id);

}
