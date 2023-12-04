package use_case.entity_helpers.dummys;

import use_case.entity_helpers.PlayerEvaluator;
import entity.Team;
import use_case.entity_helpers.TeamEvaluator;

public class TeamEvaluatorDummy extends TeamEvaluator {
    public TeamEvaluatorDummy(PlayerEvaluator playerEvaluator) {
        super(playerEvaluator);
    }

    @Override
    public float evaluateTeam(Team t) {
        float teamScore = 0;
        for (entity.Player p : t.getTeamPlayers()) {
            teamScore += playerEvaluator.evaluatePlayer(p, null);
        }
        return teamScore;
    }
}
