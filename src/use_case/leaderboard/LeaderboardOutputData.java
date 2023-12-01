package use_case.leaderboard;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class LeaderboardOutputData {

    private final boolean useCaseFailed;

    private final Pair<String[], Float[]> leaderboard;


    public LeaderboardOutputData(Pair<String[], Float[]> leaderboard, boolean useCaseFailed) {

        this.useCaseFailed = useCaseFailed;
        this.leaderboard = leaderboard;
    }

    public Pair<String[], Float[]> getLeaderboard() {
        return leaderboard;
    }

}
