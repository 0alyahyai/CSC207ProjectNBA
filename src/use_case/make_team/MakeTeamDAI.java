package use_case.make_team;

import entity.Player;
import entity.Team;
import entity.User;

import java.util.List;

public interface MakeTeamDAI {
    /**
     * Saves that team as that user's team.
     * Recall that the relationship between users and teams is one-to-one.
     * @param user The user to which the team belongs
     * @param team The team to be added as this user's team
     * @return whether the team was successfully saved
     */
    boolean saveTeam(User user, Team team);

    Team getTeamOfUser(String userID);
}
