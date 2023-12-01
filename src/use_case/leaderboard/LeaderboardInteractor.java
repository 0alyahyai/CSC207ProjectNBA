package use_case.leaderboard;

import data_access.APIDataAccessObject;
import entity.Team;
import entity.TeamComparator;
import entity.User;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardInteractor implements LeaderboardInputBoundary{

    final LeaderboardOutputBoundary leaderboardPresenter;
    final TeamComparator teamComparator;
    final LeaderboardFileUserDataAccessInterface LeaderboardDataAccessObject;
    final LeaderboardAPIaccessInterface LeaderboardAPIaccessObject;

    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardPresenter, LeaderboardFileUserDataAccessInterface leaderboardDataAccessObject, TeamComparator teamComparator) {
        this.leaderboardPresenter = leaderboardPresenter;
        this.LeaderboardDataAccessObject = leaderboardDataAccessObject;
        this.LeaderboardAPIaccessObject = new APIDataAccessObject();
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
