package use_case.make_team.player_stats;

import java.util.List;

public interface PlayerStatsDataAccessInterface {
     List<List<Integer>> extractPlayerStats(int playerid);

}
