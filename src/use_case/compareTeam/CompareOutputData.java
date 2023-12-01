package use_case.compareTeam;

import entity.Team;

import java.util.List;

public class CompareOutputData {

    private final List<Team> teams;

    public CompareOutputData( List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

}
