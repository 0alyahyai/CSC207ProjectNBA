package use_case.leaderboard;

public class LeaderboardOutputData {

    private final boolean useCaseFailed;

    private final String[] leaderboardUsers;
    private final Float[] leaderboardScores;


    public LeaderboardOutputData(String[] leaderboardUsers, Float[] leaderboardScores, boolean useCaseFailed) {

        this.useCaseFailed = useCaseFailed;
        this.leaderboardUsers = leaderboardUsers;
        this.leaderboardScores = leaderboardScores;
    }

    public String[] getLeaderboardUsers() {
        return leaderboardUsers;
    }
    public Float[] getLeaderboardScores() {return leaderboardScores;}

}
