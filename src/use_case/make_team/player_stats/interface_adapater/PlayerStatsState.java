package use_case.make_team.player_stats.interface_adapater;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatsState {

    private ArrayList<ArrayList<Integer>> playerStats;


    public PlayerStatsState() {
        ;
    }

    public void setPlayerStats(ArrayList<ArrayList<Integer>> stats){
        this.playerStats = stats;
    }

    public ArrayList<ArrayList<Integer>> getPlayerStats() {
        return this.playerStats;

    }
}
