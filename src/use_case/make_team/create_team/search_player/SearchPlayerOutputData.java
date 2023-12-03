package use_case.make_team.create_team.search_player;

import java.util.List;
import entity.Player;

public class SearchPlayerOutputData {
    private final List<Player> matchingPlayers;


    public SearchPlayerOutputData(List<Player> matchingPlayers) {
        this.matchingPlayers = matchingPlayers;
    }

    public List<Player> getMatchingPlayers() {
        return matchingPlayers;
    }
}
