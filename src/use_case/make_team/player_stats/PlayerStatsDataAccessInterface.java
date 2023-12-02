package use_case.make_team.player_stats;

import java.util.List;
import entity.Player;

public interface PlayerStatsDataAccessInterface {
     List<Float> extractPlayerStats(Player player);

}
