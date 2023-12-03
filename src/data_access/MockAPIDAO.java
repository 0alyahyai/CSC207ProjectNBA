package data_access;

import entity.Player;
import entity.Stats;

import java.util.ArrayList;
import java.util.Map;

//TODO: delete this class

public class MockAPIDAO implements APIinterface {



    @Override
    public Map<String, Object> searchPlayer(String name) {
        return null;
    }

    @Override
    public Map<String, Object> getPlayerStats(int id) {
        return null;
    }

    @Override
    public Map<String, Object> getGeneralPlayerInfo(int id) {
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

    @Override
    public ArrayList<String> viewTeamGetStats(int id) {
        return null;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getPlayerStatsforgraph(int id) {
        return null;
    }
}
