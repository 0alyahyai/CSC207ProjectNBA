package use_case.leaderboard;

import entity.TeamComparator;
import org.apache.commons.lang3.tuple.Pair;

public class LeaderboardInteractor implements LeaderboardInputBoundary{

    final LeaderboardOutputBoundary leaderboardPresenter;
    final TeamComparator teamComparator;
    final LeaderboardFileUserDataAccessInterface LeaderboardDataAccessObject;

    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardPresenter, LeaderboardFileUserDataAccessInterface leaderboardDataAccessObject, TeamComparator teamComparator) {
        this.leaderboardPresenter = leaderboardPresenter;
        this.LeaderboardDataAccessObject = leaderboardDataAccessObject;
        this.teamComparator = teamComparator;
    }
    public void back() {
        leaderboardPresenter.toMenu();
    }

    public void load() {
        String[] leaderboardUsers = LeaderboardDataAccessObject.getOrderedNames(teamComparator);
        Float[] leaderboardScores = LeaderboardDataAccessObject.getOrderedScores(teamComparator);

        Pair<String[], Float[]> leaderboard = Pair.of(leaderboardUsers, leaderboardScores);
        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(leaderboard, false);
        leaderboardPresenter.loadLeaderboard(leaderboardOutputData);
    }
}
