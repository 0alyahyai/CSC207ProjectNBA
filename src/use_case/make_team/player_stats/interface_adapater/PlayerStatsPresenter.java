package use_case.make_team.player_stats.interface_adapater;

import use_case.make_team.player_stats.PlayerStatsOutputBoundary;
import use_case.make_team.player_stats.PlayerStatsOutputData;
import view.ViewManagerModel;

public class PlayerStatsPresenter implements PlayerStatsOutputBoundary {

    private final PlayerStatsViewModel playerStatsViewModel;

    private final ViewManagerModel viewManagerModel;

    public PlayerStatsPresenter(PlayerStatsViewModel playerStatsViewModel, ViewManagerModel viewManagerModel) {
        this.playerStatsViewModel = playerStatsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(PlayerStatsOutputData playerStatsOutputData){

        PlayerStatsState playerStatsState = playerStatsViewModel.getState();

        playerStatsState.setPlayerStats(playerStatsOutputData.getPlayerStats());

        playerStatsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(playerStatsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }
}
