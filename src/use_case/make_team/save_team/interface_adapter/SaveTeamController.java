package use_case.make_team.save_team.interface_adapter;

import entity.Player;
import entity.Team;
import entity.TeamFactory;
import entity.User;
import use_case.make_team.save_team.SaveTeamInputBoundary;
import use_case.make_team.save_team.SaveTeamInputData;

import java.util.List;

public class SaveTeamController {
    private final SaveTeamInputBoundary inputBoundary;

    public SaveTeamController(SaveTeamInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void execute(User user, String teamName, List<Player> players) {
        SaveTeamInputData inputData = new SaveTeamInputData(user, players, teamName);
        inputBoundary.execute(inputData);
    }
}
