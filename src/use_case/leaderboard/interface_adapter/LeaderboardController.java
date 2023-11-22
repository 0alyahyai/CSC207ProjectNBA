package use_case.leaderboard.interface_adapter;

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
