package use_case.leaderboard.interface_adapter;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class LeaderboardState {

    private boolean loggedIn = false;
    private String leaderboardError = null;

    private String[] leaderboardUsers = null;

    private Float[] leaderboardScores = null;

    private String message = null;

    public LeaderboardState(LeaderboardState copy) {
        leaderboardError = copy.leaderboardError;
        leaderboardUsers = copy.leaderboardUsers;
        leaderboardScores = copy.leaderboardScores;
        message = copy.message;
    }

    public LeaderboardState() {}

    public String[] getLeaderboardUsers() {
        return leaderboardUsers;
    }

    public Float[] getLeaderboardScores() {
        return leaderboardScores;
    }

    public void setLeaderboard(String[] leaderboardUsers, Float[] leaderboardScores) {
        this.leaderboardUsers = leaderboardUsers;
        this.leaderboardScores = leaderboardScores;
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
