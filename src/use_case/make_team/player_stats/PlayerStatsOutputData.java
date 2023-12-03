package use_case.make_team.player_stats;

//import entity.Stats;
import java.util.ArrayList;
import java.util.List;

public class PlayerStatsOutputData {

    private ArrayList<ArrayList<Integer>> playerStats;


    public PlayerStatsOutputData() {
        ;
    }

    public void setPlayerStats(ArrayList<ArrayList<Integer>> stats){
        this.playerStats = stats;
    }

    public ArrayList<ArrayList<Integer>> getPlayerStats() {
        return this.playerStats;

    }
}
