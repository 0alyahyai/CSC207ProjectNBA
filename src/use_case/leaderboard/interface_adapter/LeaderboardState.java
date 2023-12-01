package use_case.leaderboard.interface_adapter;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class LeaderboardState {

    private boolean loggedIn = false;
    private String leaderboardError = null;
    private Pair<String[], Float[]> leaderboard = null;

    private String[] leaderboardUsers = null;

    private Float[] leaderboardScores = null;

    private String message = null;

    public LeaderboardState(LeaderboardState copy) {
        leaderboardError = copy.leaderboardError;
        leaderboard = copy.leaderboard;
        leaderboardUsers = copy.leaderboardUsers;
        leaderboardScores = copy.leaderboardScores;
        message = copy.message;
    }

    public LeaderboardState() {}

    public Pair<String[], Float[]> getLeaderboard() {
        return leaderboard;
    }
    public String[] getLeaderboardUsers() {
        return leaderboardUsers;
    }

    public Float[] getLeaderboardScores() {
        return leaderboardScores;
    }

    public void setLeaderboard(Pair<String[], Float[]> leaderboard) {
        this.leaderboard = leaderboard;
        this.leaderboardUsers = leaderboard.getLeft();
        this.leaderboardScores = leaderboard.getRight();
        this.message = convertArrayToString(this.leaderboardUsers);
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
