package use_case.leaderboard.interface_adapter;

public class LeaderboardState {

    private boolean loggedIn = false;
    private String leaderboardError = null;

    private String activeUserID = null;

    private String[] leaderboardUsers = null;
    private String[] leaderboardUserIDs = null;

    private Float[] leaderboardScores = null;

    private String message = null;

    public LeaderboardState(LeaderboardState copy) {
        leaderboardError = copy.leaderboardError;
        leaderboardUsers = copy.leaderboardUsers;
        leaderboardScores = copy.leaderboardScores;
        activeUserID = copy.activeUserID;
        message = copy.message;
    }

    public LeaderboardState() {}

    public void setActiveUserID(String activeUserID) {this.activeUserID = activeUserID;}
    public String getActiveUserID() {return activeUserID;}

    public boolean isLoggedIn() {return getActiveUserID() != null;}

    public void setLeaderboard(String[] leaderboardUsers, String[] leaderboardIDs, Float[] leaderboardScores, String activeUserID) {
        this.leaderboardUsers = leaderboardUsers;
        this.leaderboardScores = leaderboardScores;
        this.leaderboardUserIDs = leaderboardIDs;
//        this.message = convertArrayToString(this.leaderboardUsers);
        this.activeUserID = activeUserID;
    }

    public String[] getLeaderboardUsers() {
        return leaderboardUsers;
    }

    public String[] getLeaderboardUserIDs() {return leaderboardUserIDs; }

    public Float[] getLeaderboardScores() {
        return leaderboardScores;
    }
    public String getLeaderboardError() {
        return leaderboardError;
    }

    public void setLeaderboardError(String leaderboardError) {
        this.leaderboardError = leaderboardError;
        this.message = leaderboardError;
    }

//    public String getMessage() {
//        return message;
//    }
//    public String convertArrayToString(String[] leaderboard) {
//        String LeaderboardString = "";
//        for (String user : leaderboard) {
//            LeaderboardString += user + "\n";
//        }
//        return LeaderboardString;
//    }

}
