package use_case.entity_helpers;

import data_access.APIinterface;

public class TeamEvaluatorFactory {
    private final PlayerEvaluatorFactory playerEvalFactory;

    public TeamEvaluatorFactory(PlayerEvaluatorFactory playerEvalFactory) {
        this.playerEvalFactory = playerEvalFactory;
    }

    public TeamEvaluator getMarkovTeamEvaluator(APIinterface apIinterface) {
        PlayerEvaluator markovPlayerEvaluator = playerEvalFactory.getMarkovPlayerEvaluator(apIinterface);
        TeamEvaluator markovTeamEvaluator = new MarkovTeamEvaluator(markovPlayerEvaluator);
        return markovTeamEvaluator;
    }


    public TeamEvaluator getLogarithmTeamEvaluator(APIinterface apIinterface) {
        PlayerEvaluator logarithmPlayerEvaluator = playerEvalFactory.getLogarithmPlayerEvaluator(apIinterface);
        TeamEvaluator LogarithmTeamEvaluator = new LogarithmicTeamEvaluator(logarithmPlayerEvaluator);
        return LogarithmTeamEvaluator;
    }
}
