package use_case.make_team.player_stats.interface_adapater;


import entity.Player;
import use_case.make_team.player_stats.PlayerStatsInputBoundary;
import use_case.make_team.player_stats.PlayerStatsInputData;
import use_case.make_team.player_stats.PlayerStatsOutputData;


public class PlayerStatsController {

    private final PlayerStatsInputBoundary playerStatsInputBoundary;


    public PlayerStatsController(PlayerStatsInputBoundary playerStatsInputBoundary) {
        this.playerStatsInputBoundary = playerStatsInputBoundary;
    }

    public void execute(Player player){
        PlayerStatsInputData playerStatsInputData = new PlayerStatsInputData(player);
        playerStatsInputBoundary.execute(playerStatsInputData);
    }
}
