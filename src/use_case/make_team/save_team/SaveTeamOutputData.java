package use_case.make_team.save_team;

import entity.Team;
import entity.User;

public class SaveTeamOutputData {
    private final User user;
    private final Team team;

    public SaveTeamOutputData(User user, Team team) {
        this.user = user;
        this.team = team;
    }

    String getSuccessMessage() {
        return "User " + user.getUserName() + " has successfully create team " + team.getTeamName();
    }

    public User getUser() {
        return user;
    }

    public Team getTeam() {
        return team;
    }
}
