package use_case.entity_helpers;

import data_access.APIinterface;

public class PlayerEvaluatorFactory {

    public PlayerEvaluator getMarkovPlayerEvaluator(APIinterface apIinterface) {
        PlayerEvaluator markovPlayerEvaluator = new MarkovPlayerEvaluator(apIinterface);
        return markovPlayerEvaluator;

    }

    public PlayerEvaluator getBonusPlayerEvaluator(APIinterface apIinterface) {
        PlayerEvaluator logarithmPlayerEvaluator = new LogarithmicPlayerEvaluator(apIinterface);
        return logarithmPlayerEvaluator;
    }

    public PlayerEvaluator getLogarithmPlayerEvaluator(APIinterface apIinterface) {
        PlayerEvaluator bonusPlayerEvaluator = new BonusPlayerEvaluator(apIinterface);
        return bonusPlayerEvaluator;
    }
}
