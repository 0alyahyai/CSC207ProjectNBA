package use_case.make_team.create_team;

import entity.Player;
import use_case.make_team.create_team.addPlayer.AddPlayerOutputData;
import use_case.make_team.create_team.search_player.SearchPlayerOutputData;

public interface CreateTeamOutputBoundary {

    void showMatchingPlayers(SearchPlayerOutputData searchPlayerOutputData);

    void updateTeamWithNewPlayer(AddPlayerOutputData outputData);


}
