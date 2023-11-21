package use_case.leaderboard;

public interface LeaderboardOutputBoundary {
    void toMenu();

    void loadLeaderboard(LeaderboardOutputData leaderboard);
}
