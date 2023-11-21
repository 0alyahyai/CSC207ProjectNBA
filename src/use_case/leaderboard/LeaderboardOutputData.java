package use_case.leaderboard;

public class LeaderboardOutputData {

    private final boolean useCaseFailed;

    private final String[] leaderboard;

    public LeaderboardOutputData(String[] leaderboard, boolean useCaseFailed) {

        this.useCaseFailed = useCaseFailed;
        this.leaderboard = leaderboard;
    }

    public String[] getLeaderboard() {
        return leaderboard;
    }

}
