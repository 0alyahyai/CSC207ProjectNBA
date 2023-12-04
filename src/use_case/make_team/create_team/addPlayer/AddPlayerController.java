package use_case.make_team.create_team.addPlayer;

import entity.Player;

import java.util.List;

public class AddPlayerController {
    private final AddPlayerInputBoundary addPlayerUCI;

    public AddPlayerController(AddPlayerInputBoundary addPlayerUCI) {
        this.addPlayerUCI = addPlayerUCI;
    }

    public void execute(Player player, List<Player> currentTeam) {
        AddPlayerInputData addPlayerInputData = new AddPlayerInputData(player, currentTeam);
        addPlayerUCI.execute(addPlayerInputData);
    }
}
