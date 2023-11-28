package data_access;

import entity.Stats;

public class MockAPIDAO implements APIinterface {

    @Override
    public Stats getStats() {
        return null;
    }

    @Override
    public String getNameOfPlayer(int id) {
        return "SOME PLAYER NAME for id " + id;
    }
}
