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

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Team team = (CommonTeam) o;
        // field comparison
        boolean idMatch = (teamID == team.getTeamID());
        boolean nameMatch = teamName.equals(team.getTeamName());

        boolean playerMatch = true;
        List<Player> otherPlayers = team.getTeamPlayers();
        for (int i = 0; i < 5; i++) {
            if (!teamPlayers.get(i).equals(otherPlayers.get(i))) {
                playerMatch = false;
                break;
            }
        }

        return idMatch && nameMatch && playerMatch;
    }
}
