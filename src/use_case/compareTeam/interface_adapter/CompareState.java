package use_case.compareTeam.interface_adapter;

import entity.Team;

import java.util.List;

public class CompareState {
    public  final List<Team> teams;
    public boolean possible;

    public CompareState(List<Team> teams, Boolean activeHasTeam) {
        this.teams = teams;
        this.possible = !teams.isEmpty() && activeHasTeam;



    }


    public List<Team> getTeams() {
        return teams;
    }

    public boolean getPossible() {
        return possible;
    }
}
