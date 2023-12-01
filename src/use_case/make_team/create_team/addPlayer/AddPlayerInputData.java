package use_case.make_team.create_team.addPlayer;

import entity.Player;

import java.util.List;

public class AddPlayerInputData {
    private final Player playerToAdd;
    private final List<Player> currentTeam;

    public AddPlayerInputData(Player playerToAdd, List<Player> currentTeam) {
        this.playerToAdd = playerToAdd;
        this.currentTeam = currentTeam;
    }

    public Player getPlayerToAdd() {
        return playerToAdd;
    }

    public List<Player> getCurrentTeam() {
        return currentTeam;
    }
}

