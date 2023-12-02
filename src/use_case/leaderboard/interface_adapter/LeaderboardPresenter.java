package use_case.leaderboard.interface_adapter;

import use_case.menu.interface_adapter.MenuViewModel;
import view.ViewManagerModel;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.LeaderboardOutputData;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {

    private final LeaderboardViewModel leaderboardViewModel;

    private final MenuViewModel menuViewModel;
    private final ViewManagerModel viewManagerModel;

    public LeaderboardPresenter(LeaderboardViewModel leaderboardViewModel, ViewManagerModel viewManagerModel,
                                MenuViewModel menuViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
    }

    @Override
    public void toMenu() {
        viewManagerModel.setActiveView(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void toLoggedInMenu() {
        viewManagerModel.setActiveView(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void loadLeaderboard(LeaderboardOutputData leaderboard) {
        LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setLeaderboard(leaderboard.getLeaderboardUsers(), leaderboard.getLeaderboardIds(), leaderboard.getLeaderboardScores());
        leaderboardViewModel.firePropertyChanged();
    }
}
