package use_case.leaderboard;

import entity.TeamComparator;

public interface LeaderboardFileUserDataAccessInterface {
    //The following methods are used to get the data from the database, this is a stand-in as we have not yet implemented Teams

    String[] getOrderedNames(TeamComparator teamComparator);

    Float[] getOrderedScores(TeamComparator teamComparator);

    String[] getOrderedIDs(TeamComparator teamComparator);
}
