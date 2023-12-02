package entity.dummys;

import entity.PlayerEvaluator;
import entity.Team;
import java.util.Random;

public class TeamEvaluatorDummy extends entity.TeamEvaluator{
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
