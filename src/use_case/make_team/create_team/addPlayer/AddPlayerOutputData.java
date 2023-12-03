package use_case.make_team.create_team.addPlayer;

import entity.Player;

import java.util.List;

public class AddPlayerOutputData {
    private final List<Player> updatedTeam;

    public AddPlayerOutputData(List<Player> updatedTeam) {
        this.updatedTeam = updatedTeam;
    }

    public List<Player> getUpdatedTeam() {
        return updatedTeam;
    }
}
