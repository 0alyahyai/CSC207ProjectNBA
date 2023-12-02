package use_case.entity_helpers;

import data_access.APIinterface;
import entity.Team;
import use_case.algorithm.AlgorithmDataAccessInterface;

public class MarkovTeamEvaluator extends TeamEvaluator{

    public APIinterface apIinterface;

    public MarkovTeamEvaluator(PlayerEvaluator playerEvaluator, Team team) {




        super(playerEvaluator);
    }

    @Override
    public float evaluateTeam(Team t) {
        return 0;
    }
}
