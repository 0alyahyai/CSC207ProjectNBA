package use_case.entity_helpers;

import data_access.APIinterface;

public class PlayerEvaluatorFactory {

    public PlayerEvaluator getMarkovPlayerEvaluator(APIinterface apIinterface) {
        PlayerEvaluator markovPlayerEvaluator = new MarkovPlayerEvaluator(apIinterface);
        return markovPlayerEvaluator;

    }


    public PlayerEvaluator getLogarithmPlayerEvaluator(APIinterface apIinterface) {
        PlayerEvaluator logPlayerEvaluator = new LogarithmicPlayerEvaluator(apIinterface);
        return logPlayerEvaluator;
    }
}
