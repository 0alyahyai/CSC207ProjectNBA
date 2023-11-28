package use_case.make_team.save_team;

import entity.Player;
import entity.User;

import java.util.List;

public class SaveTeamInputData {
    private final User user;
    private final List<Player> players;
    private final String teamName;

    public SaveTeamInputData(User user, List<Player> players, String teamName) {
        this.user = user;
        this.players = players;
        this.teamName = teamName;
    }

    public User getUser() {
        return user;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getTeamName() {
        return teamName;
    }
}
