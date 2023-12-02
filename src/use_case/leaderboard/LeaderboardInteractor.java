package use_case.leaderboard;

import entity.TeamComparator;

public class LeaderboardInteractor implements LeaderboardInputBoundary{

    final LeaderboardOutputBoundary leaderboardPresenter;
    final TeamComparator teamComparator;
    final LeaderboardFileUserDataAccessInterface LeaderboardDataAccessObject;

    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardPresenter, LeaderboardFileUserDataAccessInterface leaderboardDataAccessObject, TeamComparator teamComparator) {
        this.leaderboardPresenter = leaderboardPresenter;
        this.LeaderboardDataAccessObject = leaderboardDataAccessObject;
        this.teamComparator = teamComparator;
    }
    public void toMenu() {
        leaderboardPresenter.toMenu();
    }
    public void toLoggedIn() {
        leaderboardPresenter.toLoggedIn();
    }

    @Override
    public void setActiveUser() {
        String[] leaderboardUsers = new String[0];
        Float[] leaderboardScores = new Float[0];
        String[] leaderboardIds = new String[0];
        String activeUserID = LeaderboardDataAccessObject.getActiveUserID();
        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(leaderboardUsers, leaderboardIds, leaderboardScores, activeUserID, true);
        leaderboardPresenter.setActiveUser(leaderboardOutputData);
    }

    public void load() {
        String[] leaderboardUsers = LeaderboardDataAccessObject.getOrderedNames(teamComparator);
        Float[] leaderboardScores = LeaderboardDataAccessObject.getOrderedScores(teamComparator);
        String[] leaderboardIds = LeaderboardDataAccessObject.getOrderedIDs(teamComparator);
        String activeUserID = LeaderboardDataAccessObject.getActiveUserID();
        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(leaderboardUsers, leaderboardIds, leaderboardScores, activeUserID, false);
        leaderboardPresenter.loadLeaderboard(leaderboardOutputData);
    }

}
