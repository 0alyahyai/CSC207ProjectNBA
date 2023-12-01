package use_case.make_team.create_team.search_player;

import data_access.APIinterface;
import entity.Player;
import use_case.make_team.MakeTeamDAI;
import use_case.make_team.create_team.CreateTeamOutputBoundary;

import java.util.List;

public class SearchPlayerUCI implements SearchPlayerInputBoundary {
    private final APIinterface apiInterface;
    private final CreateTeamOutputBoundary outputBoundary;

    public SearchPlayerUCI(APIinterface apiInterface, CreateTeamOutputBoundary outputBoundary) {
        this.apiInterface = apiInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SearchPlayerInputData inputData) {
        String writtenPlayerName = inputData.getWrittenPlayerName();

        // TODO: call api method (which needs to be implemented) to find matching players given writtenPlayerName
        List<Player> matchingPlayers = apiInterface.getAllPlayersByName();

        SearchPlayerOutputData outputData = new SearchPlayerOutputData(matchingPlayers);

        outputBoundary.showMatchingPlayers(outputData);
    }
}
