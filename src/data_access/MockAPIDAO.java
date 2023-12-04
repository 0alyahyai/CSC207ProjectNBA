package data_access;

import entity.CommonPlayerFactory;
import entity.Player;
import entity.PlayerFactory;
import use_case.entity_utilities.Stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TODO: delete this class

public class MockAPIDAO implements APIinterface {



    @Override
    public List<Player> getAllPlayersByName() {
        PlayerFactory pf = new CommonPlayerFactory();
        Player p1 = pf.createMockPlayer();
        Player p2 = pf.createMockPlayer();
        Player p3 = pf.createMockPlayer();
        Player p4 = pf.createMockPlayer();

        List<Player> list = new ArrayList<>();

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        return list;
    }

    @Override
    public List<Player> searchPlayer(String name) {
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
}
