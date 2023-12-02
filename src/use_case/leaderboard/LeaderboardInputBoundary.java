package use_case.leaderboard;

public interface LeaderboardInputBoundary {
    void toMenu();

    void load();

    void toLoggedIn();

    void setActiveUser();
}
