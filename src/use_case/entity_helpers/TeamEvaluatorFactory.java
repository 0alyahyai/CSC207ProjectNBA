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

    public TeamEvaluator getBonusTeamEvaluator(APIinterface apIinterface) {
        PlayerEvaluator bonusPlayerEvaluator = playerEvalFactory.getBonusPlayerEvaluator(apIinterface);
        TeamEvaluator bonusTeamEvaluator = new MarkovTeamEvaluator(bonusPlayerEvaluator);
        return bonusTeamEvaluator;
    }

    public TeamEvaluator getLogarithmTeamEvaluator(APIinterface apIinterface) {
        PlayerEvaluator LogarithmPlayerEvaluator = playerEvalFactory.getLogarithmPlayerEvaluator(apIinterface);
        TeamEvaluator LogarithmTeamEvaluator = new MarkovTeamEvaluator(LogarithmPlayerEvaluator);
        return LogarithmTeamEvaluator;
    }
}
