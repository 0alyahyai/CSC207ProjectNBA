package use_case.make_team.create_team;

import entity.Player;

import java.util.List;

public class CreateTeamState {
    private String teamName;
    private String searchedPlayer; // the name of the player currently being searched
    private List<Player> teamSoFar;
    private List<Player> matchingPlayers; // Players matching to the 'searchedPlayer' string

    // Getters and Setters
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSearchedPlayer() {
        return searchedPlayer;
    }

    public void setSearchedPlayer(String searchedPlayer) {
        this.searchedPlayer = searchedPlayer;
    }

    public List<Player> getTeamSoFar() {
        return teamSoFar;
    }

    public void setTeamSoFar(List<Player> teamSoFar) {
        this.teamSoFar = teamSoFar;
    }

    public List<Player> getMatchingPlayers() {
        return matchingPlayers;
    }

    public void setMatchingPlayers(List<Player> matchingPlayers) {
        this.matchingPlayers = matchingPlayers;
    }

    public boolean teamIsFull() {
        return (teamSoFar.size() == 5);
    }

}
