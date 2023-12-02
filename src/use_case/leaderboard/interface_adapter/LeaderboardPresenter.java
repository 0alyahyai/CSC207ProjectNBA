package use_case.leaderboard.interface_adapter;

import view.ViewManagerModel;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.LeaderboardOutputData;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {

    private final LeaderboardViewModel leaderboardViewModel;

    private final String menuViewName;

    private final String loggedInViewName;
    private final ViewManagerModel viewManagerModel;

    public LeaderboardPresenter(LeaderboardViewModel leaderboardViewModel, ViewManagerModel viewManagerModel,
                                String menuViewName, String loggedInViewName) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.viewManagerModel = viewManagerModel;
        this.menuViewName = menuViewName;
        this.loggedInViewName = loggedInViewName;
    }

    @Override
    public void toMenu() {
        viewManagerModel.setActiveView(menuViewName);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void toLoggedIn() {
        viewManagerModel.setActiveView(loggedInViewName);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void loadLeaderboard(LeaderboardOutputData leaderboard) {
        LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setLeaderboard(leaderboard.getLeaderboardUsers(), leaderboard.getLeaderboardIds(),
                leaderboard.getLeaderboardScores(), leaderboard.getActiveUserID());
        leaderboardViewModel.firePropertyChanged();
    }

    @Override
    public void setActiveUser(LeaderboardOutputData leaderboard) {
        LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setActiveUserID(leaderboard.getActiveUserID());
        leaderboardViewModel.firePropertyChanged();
    }
}
