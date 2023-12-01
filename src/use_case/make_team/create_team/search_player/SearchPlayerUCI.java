package use_case.make_team.create_team.search_player;

import entity.Player;
import use_case.make_team.MakeTeamDAI;
import use_case.make_team.create_team.CreateTeamOutputBoundary;

import java.util.List;

public class SearchPlayerUCI implements SearchPlayerInputBoundary {
    private final MakeTeamDAI dao;
    private final CreateTeamOutputBoundary outputBoundary;

    public SearchPlayerUCI(MakeTeamDAI dao, CreateTeamOutputBoundary outputBoundary) {
        this.dao = dao;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SearchPlayerInputData inputData) {
        String writtenPlayerName = inputData.getWrittenPlayerName();

        List<Player> matchingPlayers = null; // TODO: call api method to find matching players given writtenPlayerName

        SearchPlayerOutputData outputData = new SearchPlayerOutputData(matchingPlayers);

        outputBoundary.showMatchingPlayers(outputData);
    }
}
