package data_access;

import entity.Stats;

import java.util.Map;

public interface APIinterface {

    Stats getStats();

    String getNameOfPlayer(int id);

}
