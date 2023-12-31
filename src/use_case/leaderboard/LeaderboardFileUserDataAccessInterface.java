package use_case.leaderboard;

import use_case.entity_helpers.TeamComparator;

public interface LeaderboardFileUserDataAccessInterface {
    //The following methods are used to get the data from the database, this is a stand-in as we have not yet implemented Teams

    //Returns the names of the users in the database in order of their teams score. Only returns users with teams,
    //If no users getOrderedNames returns null, but the other methods will still return arrays of the same length 0

    void updateUserScores(TeamComparator teamComparator);
    String[] getOrderedNames();

    Float[] getOrderedScores();

    String[] getOrderedIDs();

    String getActiveUserID();
}
