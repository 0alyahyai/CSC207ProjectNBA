package use_case.entity_helpers;

import data_access.APIinterface;
import entity.Team;
import use_case.algorithm.AlgorithmDataAccessInterface;

public class MarkovTeamEvaluator extends TeamEvaluator{

    public APIinterface apIinterface;

    public MarkovTeamEvaluator(PlayerEvaluator playerEvaluator) {
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
