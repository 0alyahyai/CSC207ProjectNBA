package use_case.entity_helpers.dummys;

import entity.Team;
import use_case.entity_helpers.TeamComparator;
import use_case.entity_helpers.TeamEvaluator;

public class LeaderboardTeamComparator extends TeamComparator {
    public LeaderboardTeamComparator(TeamEvaluator teamEvaluator) {
        super(teamEvaluator);
    }

    @Override
    public Team compareTeams(Team t1, Team t2) {
        return null;
    }


}
