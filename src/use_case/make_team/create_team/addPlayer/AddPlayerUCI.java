package use_case.make_team.create_team.addPlayer;

import entity.Player;
import use_case.make_team.create_team.CreateTeamOutputBoundary;

import java.util.List;

public class AddPlayerUCI implements AddPlayerInputBoundary {

    private final CreateTeamOutputBoundary addPlayerOutputBoundary;

    public AddPlayerUCI(CreateTeamOutputBoundary addPlayerOutputBoundary) {
        this.addPlayerOutputBoundary = addPlayerOutputBoundary;
    }

    @Override
    public void execute(AddPlayerInputData inputData) {
        List<Player> currentTeam = inputData.getCurrentTeam();
        Player playerToAdd = inputData.getPlayerToAdd();

        if (currentTeam.size()==5) {
            addPlayerOutputBoundary.prepareFullTeamFailView();
        }
        else {
            currentTeam.add(playerToAdd);

            AddPlayerOutputData outputData = new AddPlayerOutputData(currentTeam);
            addPlayerOutputBoundary.updateTeamWithNewPlayer(outputData);
        }


    }
}
