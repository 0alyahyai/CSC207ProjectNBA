package use_case.make_team.player_stats;

public class PlayerStatsInteractor implements PlayerStatsInputBoundary{

    private final PlayerStatsInputData playerStatsInputData;

    private final PlayerStatsOutputData playerStatsOutputData;

    private final PlayerStatsOutputBoundary playerStatsOutputBoundary;

    private final PlayerStatsDataAccessInterface playerStatsDataAccessInterface;

    public PlayerStatsInteractor(PlayerStatsInputData playerStatsInputData, PlayerStatsOutputData playerStatsOutputData, PlayerStatsOutputBoundary playerStatsOutputBoundary, PlayerStatsDataAccessInterface playerStatsDataAccessInterface) {
        this.playerStatsInputData = playerStatsInputData;
        this.playerStatsOutputData = playerStatsOutputData;
        this.playerStatsOutputBoundary = playerStatsOutputBoundary;

        this.playerStatsDataAccessInterface = playerStatsDataAccessInterface;
    }

    @Override
    public void execute() {


    }
}
