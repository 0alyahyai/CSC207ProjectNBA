package use_case.compareTeam;

import entity.Team;

import java.util.List;

public class CompareOutputData {

    private final List<Team> teams;
    private final Boolean activeHasTeam;

    public CompareOutputData( List<Team> teams, Boolean activeHasTeam) {
        this.teams = teams;
        this.activeHasTeam = activeHasTeam;
    }

    public List<Team> getTeams() {
        return teams;
    }
    public Boolean getactiveHasTeam(){return activeHasTeam;}

}
