package data_access;

import entity.Player;
import entity.Stats;

public class APIDataAccessObject implements APIinterface {

    public APIDataAccessObject() {
    }

    @Override
    public Stats getStats() {
        return null;
    }

    //TODO: properly implement this method
    // currently returns a string array of length 7
    @Override
    public String[] viewTeamGetStats(Player player) {
        return new String[]{"1", "2", "3", "4", "5", "6", "07"};
    }
}
