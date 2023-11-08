package entity;

import java.util.List;

class CommonTeam implements Team{

    private final String teamName;

    private final int teamID;

    //exactly 5 players
    private List<Player> teamPlayers;

    CommonTeam(String teamName, int teamID, List<Player> teamPlayers) {

        this.teamName = teamName;
        this.teamID = teamID;
        this.teamPlayers = teamPlayers;
    }

    @Override
    public String getTeamName() {
        return teamName;
    }

    @Override
    public int getTeamID() {
        return teamID;
    }

    @Override
    public List<Player> getTeamPlayers() {
        return teamPlayers;
    }
}
