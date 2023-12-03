package use_case.make_team.player_stats.interface_adapater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerStatsState {


    private ArrayList<ArrayList<Integer>> playerStats;


    public PlayerStatsState() {


        ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<>();
        listOfLists.add(new ArrayList<>(Arrays.asList(1, 3, 5, 7)));
        listOfLists.add(new ArrayList<>(Arrays.asList(2, 4, 6, 8)));
        listOfLists.add(new ArrayList<>(Arrays.asList(10, 9, 8, 7)));
        listOfLists.add(new ArrayList<>(Arrays.asList(3, 6, 9, 12)));

        playerStats = listOfLists;

        ;
    }

    public void setPlayerStats(ArrayList<ArrayList<Integer>> stats){
        this.playerStats = stats;
    }

    public ArrayList<ArrayList<Integer>> getPlayerStats() {
        return this.playerStats;

    }
}
