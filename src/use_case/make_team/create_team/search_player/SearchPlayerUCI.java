package use_case.make_team.create_team.search_player;

import data_access.APIinterface;
import entity.Player;
import entity.PlayerFactory;
import use_case.make_team.create_team.CreateTeamOutputBoundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchPlayerUCI implements SearchPlayerInputBoundary {
    private final APIinterface apiInterface;
    private final CreateTeamOutputBoundary outputBoundary;

    private final PlayerFactory playerFactory;

    public SearchPlayerUCI(APIinterface apiInterface, CreateTeamOutputBoundary outputBoundary, PlayerFactory playerFactory) {
        this.apiInterface = apiInterface;
        this.outputBoundary = outputBoundary;
        this.playerFactory = playerFactory;
    }

    @Override
    public void execute(SearchPlayerInputData inputData) {
        String writtenPlayerName = inputData.getWrittenPlayerName();

        List<Player> matchingPlayers = apiInterface.searchPlayer(writtenPlayerName);

        if (matchingPlayers == null) {
            matchingPlayers = new ArrayList<>();
        }

        SearchPlayerOutputData outputData = new SearchPlayerOutputData(matchingPlayers);

        outputBoundary.showMatchingPlayers(outputData);
    }
}