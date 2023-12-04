package use_case.make_team.player_stats;


public class PlayerStatsInputData {

    private final int playerid;


    public PlayerStatsInputData(int playerid) {
        this.playerid = playerid;
    }

    public int getPlayerid() {
        return this.playerid;
    }
}
