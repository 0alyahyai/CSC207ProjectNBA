package use_case.leaderboard;


import use_case.menu.MenuOutputBoundary;

public class LeaderboardInteractor implements LeaderboardInputBoundary{

    final LeaderboardOutputBoundary leaderboardPresenter;
    final LeaderboardDataAccessInterface LeaderboardDataAccessObject;

    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardPresenter, LeaderboardDataAccessInterface leaderboardDataAccessObject) {
        this.leaderboardPresenter = leaderboardPresenter;
        this.LeaderboardDataAccessObject = leaderboardDataAccessObject;
    }
    public void back() {
        leaderboardPresenter.toMenu();
    }

    public void load() {
        String[] leaderboard = LeaderboardDataAccessObject.getUserswithTeam();
        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(leaderboard, false);
        leaderboardPresenter.loadLeaderboard(leaderboardOutputData);
    }
}
