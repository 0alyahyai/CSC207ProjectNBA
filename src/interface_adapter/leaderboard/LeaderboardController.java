package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.menu.MenuInputBoundary;

public class LeaderboardController {

    final LeaderboardInputBoundary leaderboardUseCaseInteractor;

    public LeaderboardController(LeaderboardInputBoundary leaderboardUseCaseInteractor) {
        this.leaderboardUseCaseInteractor = leaderboardUseCaseInteractor;
    }
    public void back () {
        leaderboardUseCaseInteractor.back();
    }
}
