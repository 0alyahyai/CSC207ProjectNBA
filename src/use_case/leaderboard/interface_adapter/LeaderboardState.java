package use_case.leaderboard.interface_adapter;

public class LeaderboardState {
    private String leaderboardError = null;

    private String[] leaderboard = null;

    private String message = null;

    public LeaderboardState(LeaderboardState copy) {
        leaderboardError = copy.leaderboardError;
        leaderboard = copy.leaderboard;
        message = copy.message;
    }

    public LeaderboardState() {}

    public String[] getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(String[] leaderboard) {
        this.leaderboard = leaderboard;
        this.message = convertArrayToString(leaderboard);
    }
    public String getLeaderboardError() {
        return leaderboardError;
    }

    public void setLeaderboardError(String leaderboardError) {
        this.leaderboardError = leaderboardError;
        this.message = leaderboardError;
    }

    public String getMessage() {
        return message;
    }
    public String convertArrayToString(String[] leaderboard) {
        String LeaderboardString = "";
        for (String user : leaderboard) {
            LeaderboardString += user + "\n";
        }
        return LeaderboardString;
    }

}
