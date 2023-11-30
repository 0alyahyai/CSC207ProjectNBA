package data_access;

import entity.Player;
import entity.Stats;

public class MockAPIDAO implements APIinterface {

    @Override
    public String[] viewTeamGetStats(Player player) {
        return new String[0];
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
