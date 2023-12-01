package use_case.leaderboard;

import entity.Team;

import java.util.List;

public interface LeaderboardAPIaccessInterface {
    Team makeTeamFromIdList(List<Integer> idList, String teamId);
}
