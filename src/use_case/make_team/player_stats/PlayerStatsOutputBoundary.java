package use_case.make_team.player_stats;

public interface PlayerStatsOutputBoundary {

    void prepareSuccessView(PlayerStatsOutputData playerStatsOutputData);

    void prepareFailView(PlayerStatsOutputData playerStatsOutputData);


}
