package use_case.leaderboard;

import entity.Player;
import entity.User;

public interface LeaderboardDataAccessInterface {
    //The following methods are used to get the data from the database, this is a standin as we have not yet implemented Teams
    String[] getUserswithTeam();
}
