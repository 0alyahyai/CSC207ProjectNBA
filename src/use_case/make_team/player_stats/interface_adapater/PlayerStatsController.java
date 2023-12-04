package use_case.make_team.player_stats.interface_adapater;


import use_case.make_team.player_stats.PlayerStatsInputBoundary;
import use_case.make_team.player_stats.PlayerStatsInputData;


public class PlayerStatsController {

    private final PlayerStatsInputBoundary playerStatsInputBoundary;


    public PlayerStatsController(PlayerStatsInputBoundary playerStatsInputBoundary) {
        this.playerStatsInputBoundary = playerStatsInputBoundary;
    }

    public void execute(int playerid){
        PlayerStatsInputData playerStatsInputData = new PlayerStatsInputData(playerid);
        playerStatsInputBoundary.execute(playerStatsInputData);
    }
}

