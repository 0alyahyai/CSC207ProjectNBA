package data_access;

import entity.Player;
import entity.Stats;

import java.util.Map;

public class MockAPIDAO implements APIinterface {

    @Override
    public String[] viewTeamGetStats(Player player) {
        return new String[0];
    }

    @Override
    public Map<String, Object> searchPlayer(String name) {
        return null;
    }

    @Override
    public Map<String, Object> getPlayerStats(int id) {
        return null;
    }

    @Override
    public Stats getStats() {
        return null;
    }

    @Override
    public String getNameOfPlayer(int id) {
        return "SOME PLAYER NAME for id " + id;
    }
}
