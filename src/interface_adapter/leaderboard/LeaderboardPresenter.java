package interface_adapter.leaderboard;

import interface_adapter.Menu.MenuViewModel;
import interface_adapter.ViewManagerModel;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.LeaderboardOutputData;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {

    private final LeaderboardViewModel leaderboardViewModel;

    private final MenuViewModel menuViewModel;
    private ViewManagerModel viewManagerModel;

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

    @Override
    public void loadLeaderboard(LeaderboardOutputData leaderboard) {
        LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setLeaderboard(leaderboard.getLeaderboard());
        leaderboardViewModel.firePropertyChanged();
    }
}
