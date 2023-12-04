package use_case.make_team.player_stats;

//import entity.Stats;
import java.util.ArrayList;

public class PlayerStatsOutputData {

    private ArrayList<ArrayList<Double>> playerStats;


    public PlayerStatsOutputData() {
        ;
    }

    public void setPlayerStats(ArrayList<ArrayList<Double>> stats){
        this.playerStats = stats;
    }

    public ArrayList<ArrayList<Double>> getPlayerStats() {
        return this.playerStats;

    }
}
