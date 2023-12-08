package use_case.make_team.player_stats;

import data_access.APIinterface;


public class PlayerStatsInteractor implements PlayerStatsInputBoundary{


    private final PlayerStatsOutputBoundary playerStatsOutputBoundary;

    private final APIinterface apIinterface;


public PlayerStatsInteractor(PlayerStatsOutputBoundary playerStatsOutputBoundary, APIinterface apIinterface ) {
        this.playerStatsOutputBoundary = playerStatsOutputBoundary;
        this.apIinterface = apIinterface;

    }

    @Override
    public void execute(PlayerStatsInputData playerStatsInputData) {

        int playerid = playerStatsInputData.getPlayerid();



        PlayerStatsOutputData playerStatsOutputData = new PlayerStatsOutputData();
        playerStatsOutputData.setPlayerStats(apIinterface.getPlayerStatsforgraph(playerid));
        playerStatsOutputBoundary.prepareSuccessView(playerStatsOutputData);

    }
}
