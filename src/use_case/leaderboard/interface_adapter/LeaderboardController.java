package use_case.leaderboard.interface_adapter;

import use_case.leaderboard.LeaderboardInputBoundary;

public class LeaderboardController {

    final LeaderboardInputBoundary leaderboardUseCaseInteractor;

    public LeaderboardController(LeaderboardInputBoundary leaderboardUseCaseInteractor) {
        this.leaderboardUseCaseInteractor = leaderboardUseCaseInteractor;
    }
    public void toMenu () {
        leaderboardUseCaseInteractor.toMenu();
    }

    public void toLoggedIn () {
        leaderboardUseCaseInteractor.toLoggedIn();
    }

    public void setActiveUser () {
        leaderboardUseCaseInteractor.setActiveUser();
    }

    public void load () {
        leaderboardUseCaseInteractor.load();
    }
}
