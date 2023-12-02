package use_case.make_team.player_stats;

//import entity.Stats;
import java.util.List;

public class PlayerStatsOutputData {

    private List<Float> playerStats;


    public PlayerStatsOutputData() {
        ;
    }

    public void setPlayerStats(List<Float> stats){
        this.playerStats = stats;
    }

    public List<Float> getPlayerStats() {
        return this.playerStats;

    }
}
