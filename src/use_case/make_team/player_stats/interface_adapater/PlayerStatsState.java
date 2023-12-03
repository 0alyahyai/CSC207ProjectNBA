package use_case.make_team.player_stats.interface_adapater;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerStatsState {


    private ArrayList<ArrayList<Double>> playerStats;


    public PlayerStatsState() {
        ;
    }

    public void setPlayerStats(ArrayList<ArrayList<Double>> stats){
        this.playerStats = stats;
    }

    public ArrayList<ArrayList<Double>> getPlayerStats() {
        return this.playerStats;

    }
}
