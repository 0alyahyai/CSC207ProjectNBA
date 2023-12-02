package entity;

import java.util.ArrayList;
import java.util.List;

public interface Team {

    String getTeamName();

    int getTeamID();

    // A team has exactly five players
    List<Player> getTeamPlayers();
}
