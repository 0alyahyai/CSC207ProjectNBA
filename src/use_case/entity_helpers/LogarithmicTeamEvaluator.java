package use_case.entity_helpers;

import data_access.APIinterface;
import entity.Team;

public class LogarithmicTeamEvaluator extends TeamEvaluator{


    public LogarithmicTeamEvaluator(PlayerEvaluator playerEvaluator) {
        super(playerEvaluator);
    }

    @Override
    public float evaluateTeam(Team team) {
        float subtotal = 0;
        for(int i = 0; i < 5; i++){
            float playerScore = playerEvaluator.evaluatePlayer(team.getTeamPlayers().get(i));
            subtotal = subtotal + playerScore;
        }
        return subtotal/5; //the average of the five players
    }
}
