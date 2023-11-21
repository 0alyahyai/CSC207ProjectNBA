package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;

public class LeaderboardController {

    final LeaderboardInputBoundary leaderboardUseCaseInteractor;

    public LeaderboardController(LeaderboardInputBoundary leaderboardUseCaseInteractor) {
        this.leaderboardUseCaseInteractor = leaderboardUseCaseInteractor;
    }
    public void back () {
        leaderboardUseCaseInteractor.back();
    }

    public void load () {
        leaderboardUseCaseInteractor.load();
    }
}
