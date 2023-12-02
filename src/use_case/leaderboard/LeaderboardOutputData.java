package use_case.leaderboard;

public class LeaderboardOutputData {

    private final boolean useCaseFailed;

    private final String[] leaderboardUsers;
    private final String[] leaderboardIds;
    private final Float[] leaderboardScores;
    private final Object activeUserID;


    public LeaderboardOutputData(String[] leaderboardUsers, String[] leaderboardIds, Float[] leaderboardScores, String activeUserID, boolean useCaseFailed) {

        this.useCaseFailed = useCaseFailed;
        this.leaderboardUsers = leaderboardUsers;
        this.leaderboardIds = leaderboardIds;
        this.leaderboardScores = leaderboardScores;
        this.activeUserID = activeUserID;
    }

    public String[] getLeaderboardUsers() {
        return leaderboardUsers;
    }

    public String[] getLeaderboardIds() { return leaderboardIds; }

    public Float[] getLeaderboardScores() {return leaderboardScores;}
    public String getActiveUserID() { return (String) activeUserID; }

}
