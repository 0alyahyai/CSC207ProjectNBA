package use_case.leaderboard;

import entity.TeamComparator;
import entity.User;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface LeaderboardFileUserDataAccessInterface {
    //The following methods are used to get the data from the database, this is a standin as we have not yet implemented Teams

    List<User> getUserswithTeam();

    List<Pair<String, Float>> getOrderedNameScores(TeamComparator teamComparator);

    String[] getOrderedNames(TeamComparator teamComparator);

    Float[] getOrderedScores(TeamComparator teamComparator);
}
