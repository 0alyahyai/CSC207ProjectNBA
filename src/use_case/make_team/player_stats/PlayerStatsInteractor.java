package use_case.make_team.player_stats;

import entity.Player;

public class PlayerStatsInteractor implements PlayerStatsInputBoundary{

    private final PlayerStatsInputData playerStatsInputData;

    private final PlayerStatsOutputBoundary playerStatsOutputBoundary;

    private final PlayerStatsDataAccessInterface playerStatsDataAccessInterface;

    public PlayerStatsInteractor(PlayerStatsInputData playerStatsInputData, PlayerStatsOutputData playerStatsOutputData, PlayerStatsOutputBoundary playerStatsOutputBoundary, PlayerStatsDataAccessInterface playerStatsDataAccessInterface) {
        this.playerStatsInputData = playerStatsInputData;
        this.playerStatsOutputBoundary = playerStatsOutputBoundary;

        this.playerStatsDataAccessInterface = playerStatsDataAccessInterface;
    }

    @Override
    public void execute(PlayerStatsInputData playerStatsInputData) {

        Player player = playerStatsInputData.getPlayer();

        PlayerStatsOutputData playerStatsOutputData = new PlayerStatsOutputData();
        playerStatsOutputData.setPlayerStats(playerStatsDataAccessInterface.extractPlayerStats(player));
        playerStatsOutputBoundary.prepareSuccessView(playerStatsOutputData);

    }
}
