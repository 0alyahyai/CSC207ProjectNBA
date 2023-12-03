package use_case.make_team.create_team;

import entity.Player;
import use_case.make_team.create_team.addPlayer.AddPlayerOutputData;
import use_case.make_team.create_team.search_player.SearchPlayerOutputData;

public class CreateTeamPresenter implements CreateTeamOutputBoundary{

    private final CreateTeamViewModel createTeamViewModel;

    public CreateTeamPresenter(CreateTeamViewModel createTeamViewModel) {
        this.createTeamViewModel = createTeamViewModel;
    }

    @Override
    public void showMatchingPlayers(SearchPlayerOutputData searchPlayerOutputData) {
//        CreateTeamState state = createTeamViewModel.getState();
//        state.setMatchingPlayers(searchPlayerOutputData.getMatchingPlayers());
//        createTeamViewModel.firePropertyChanged();
        createTeamViewModel.setSearchResults(searchPlayerOutputData.getMatchingPlayers());
    }


    @Override
    public void updateTeamWithNewPlayer(AddPlayerOutputData addPlayerOutputData) {
//        CreateTeamState state = createTeamViewModel.getState();
//        state.setTeamSoFar(addPlayerOutputData.getUpdatedTeam());
//        createTeamViewModel.firePropertyChanged();
        createTeamViewModel.setCurrentTeam(addPlayerOutputData.getUpdatedTeam());
    }

}
