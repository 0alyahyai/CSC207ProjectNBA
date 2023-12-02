package use_case.leaderboard;

public interface LeaderboardOutputBoundary {
    void toMenu();

    void toLoggedIn();

    void loadLeaderboard(LeaderboardOutputData leaderboard);
}
