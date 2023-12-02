package use_case.make_team.player_stats.interface_adapater;

import java.util.List;

public class PlayerStatsState {

    private List<Float> playerStats;


    public PlayerStatsState() {
        ;
    }

    public void setPlayerStats(List<Float> stats){
        this.playerStats = stats;
    }

    public List<Float> getPlayerStats() {
        return this.playerStats;

    }
}
