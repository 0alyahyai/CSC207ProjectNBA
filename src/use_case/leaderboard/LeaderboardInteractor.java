package use_case.leaderboard;

import use_case.menu.MenuOutputBoundary;

public class LeaderboardInteractor implements LeaderboardInputBoundary{

    final LeaderboardOutputBoundary leaderboardPresenter;

    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardPresenter) {
        this.leaderboardPresenter = leaderboardPresenter;
    }
    public void back() {
        leaderboardPresenter.toMenu();
    }
}
